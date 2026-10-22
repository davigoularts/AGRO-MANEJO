package api.service;

import api.dto.ColheitaRequestDTO;
import api.dto.ColheitaResponseDTO;
import api.mapper.ColheitaMapper;
import api.model.Colheita;
import api.model.Lote;
import api.repository.ColheitaRepository;
import api.repository.LoteRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

// CAMADA: Service
// RESPONSABILIDADE: registrar, listar e buscar colheitas
// REGRAS:
//   RN01 - o lote da colheita precisa existir (senão 404) e a quantidade colhida
//          não pode passar de 100 sacas por hectare do lote (senão 400)
// ROTAS ATENDIDAS: as de /colheitas em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Service
public class ColheitaService {

    private static final BigDecimal MAXIMO_SACAS_POR_HECTARE = new BigDecimal("100");

    private final ColheitaRepository colheitaRepository;
    private final LoteRepository loteRepository;
    private final ColheitaMapper mapper;

    public ColheitaService(ColheitaRepository colheitaRepository, LoteRepository loteRepository,
                           ColheitaMapper mapper) {
        this.colheitaRepository = colheitaRepository;
        this.loteRepository = loteRepository;
        this.mapper = mapper;
    }

    public ColheitaResponseDTO registrar(ColheitaRequestDTO dto) {
        Lote lote = loteRepository.findById(dto.loteId())
                .orElseThrow(() -> new LoteNaoEncontradoException(dto.loteId()));

        BigDecimal capacidade = lote.getHectares().multiply(MAXIMO_SACAS_POR_HECTARE);
        if (dto.quantidade().compareTo(capacidade) > 0) {
            throw new RegraDeNegocioException("RN01: a quantidade colhida (" + dto.quantidade()
                    + ") passa da capacidade do lote (" + capacidade + " sacas)");
        }

        Colheita colheita = colheitaRepository.save(mapper.toEntity(dto, lote));
        return mapper.toResponse(colheita);
    }

    public List<ColheitaResponseDTO> listar() {
        return colheitaRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ColheitaResponseDTO buscarPorId(Long id) {
        Colheita colheita = colheitaRepository.findById(id)
                .orElseThrow(() -> new ColheitaNaoEncontradaException(id));
        return mapper.toResponse(colheita);
    }
}
