package api.service;

import api.dto.VendaRequestDTO;
import api.dto.VendaResponseDTO;
import api.mapper.VendaMapper;
import api.model.Colheita;
import api.model.Comprador;
import api.model.Venda;
import api.repository.ColheitaRepository;
import api.repository.CompradorRepository;
import api.repository.VendaRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

// CAMADA: Service
// RESPONSABILIDADE: registrar, listar e buscar vendas
// REGRAS:
//   RN02 - o preço da venda não pode divergir mais de 50% do preço combinado com o comprador
//          (e a planta vendida precisa ser a que o comprador negociou)
//   RN03 - não se pode vender mais do que o estoque da planta: o que foi colhido
//          nos lotes menos o que já foi vendido
// ROTAS ATENDIDAS: as de /vendas em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Service
public class VendaService {

    private static final BigDecimal LIMITE_DIVERGENCIA = new BigDecimal("0.50");

    private final VendaRepository vendaRepository;
    private final CompradorRepository compradorRepository;
    private final ColheitaRepository colheitaRepository;
    private final VendaMapper mapper;

    public VendaService(VendaRepository vendaRepository, CompradorRepository compradorRepository,
                        ColheitaRepository colheitaRepository, VendaMapper mapper) {
        this.vendaRepository = vendaRepository;
        this.compradorRepository = compradorRepository;
        this.colheitaRepository = colheitaRepository;
        this.mapper = mapper;
    }

    public VendaResponseDTO registrar(VendaRequestDTO dto) {
        Comprador comprador = compradorRepository.findById(dto.compradorId())
                .orElseThrow(() -> new CompradorNaoEncontradoException(dto.compradorId()));

        validarPreco(dto, comprador);
        validarEstoque(dto);

        Venda venda = vendaRepository.save(mapper.toEntity(dto, comprador));
        return mapper.toResponse(venda);
    }

    public List<VendaResponseDTO> listar() {
        return vendaRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    public VendaResponseDTO buscarPorId(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new VendaNaoEncontradaException(id));
        return mapper.toResponse(venda);
    }

    // RN02: compara o preço da venda com o preço combinado no cadastro do comprador
    private void validarPreco(VendaRequestDTO dto, Comprador comprador) {
        if (!dto.planta().equalsIgnoreCase(comprador.getPlantaPreco())) {
            throw new RegraDeNegocioException("RN02: o comprador negociou preço para "
                    + comprador.getPlantaPreco() + ", não para " + dto.planta());
        }
        BigDecimal diferenca = dto.preco().subtract(comprador.getPreco()).abs();
        BigDecimal limite = comprador.getPreco().multiply(LIMITE_DIVERGENCIA);
        if (diferenca.compareTo(limite) > 0) {
            throw new RegraDeNegocioException("RN02: o preço " + dto.preco()
                    + " diverge mais de 50% do preço combinado (" + comprador.getPreco() + ")");
        }
    }

    // RN03: estoque da planta = total colhido nos lotes - total já vendido
    private void validarEstoque(VendaRequestDTO dto) {
        BigDecimal colhido = BigDecimal.ZERO;
        for (Colheita colheita : colheitaRepository.findByLotePlantaIgnoreCase(dto.planta())) {
            colhido = colhido.add(colheita.getQuantidade());
        }

        BigDecimal vendido = BigDecimal.ZERO;
        for (Venda venda : vendaRepository.findByPlantaIgnoreCase(dto.planta())) {
            vendido = vendido.add(venda.getQuantidade());
        }

        BigDecimal estoque = colhido.subtract(vendido);
        if (dto.quantidade().compareTo(estoque) > 0) {
            throw new RegraDeNegocioException("RN03: estoque insuficiente de " + dto.planta()
                    + " (disponível: " + estoque + ", pedido: " + dto.quantidade() + ")");
        }
    }
}
