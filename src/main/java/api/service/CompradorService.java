package api.service;

import api.dto.CompradorRequestDTO;
import api.dto.CompradorResponseDTO;
import api.mapper.CompradorMapper;
import api.model.Comprador;
import api.repository.CompradorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// CAMADA: Service
// RESPONSABILIDADE: cadastrar, listar e buscar compradores
// REGRAS: não permite dois compradores com o mesmo CNPJ (400); id inexistente retorna 404
// ROTAS ATENDIDAS: as de /compradores em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Service
public class CompradorService {

    private final CompradorRepository repository;
    private final CompradorMapper mapper;

    public CompradorService(CompradorRepository repository, CompradorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CompradorResponseDTO cadastrar(CompradorRequestDTO dto) {
        Comprador comprador = mapper.toEntity(dto);
        if (repository.existsByCnpj(comprador.getCnpj())) {
            throw new RegraDeNegocioException("Já existe um comprador com o CNPJ " + comprador.getCnpj());
        }
        return mapper.toResponse(repository.save(comprador));
    }

    public List<CompradorResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public CompradorResponseDTO buscarPorId(Long id) {
        Comprador comprador = repository.findById(id)
                .orElseThrow(() -> new CompradorNaoEncontradoException(id));
        return mapper.toResponse(comprador);
    }
}
