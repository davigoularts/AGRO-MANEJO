package api.service;

import api.dto.LoteRequestDTO;
import api.dto.LoteResponseDTO;
import api.mapper.LoteMapper;
import api.model.Lote;
import api.repository.LoteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// CAMADA: Service
// RESPONSABILIDADE: cadastrar, listar e buscar lotes
// REGRAS: busca por id de lote inexistente lança LoteNaoEncontradoException (404)
// ROTAS ATENDIDAS: as de /lotes em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Service
public class LoteService {

    private final LoteRepository repository;
    private final LoteMapper mapper;

    public LoteService(LoteRepository repository, LoteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public LoteResponseDTO cadastrar(LoteRequestDTO dto) {
        Lote lote = repository.save(mapper.toEntity(dto));
        return mapper.toResponse(lote);
    }

    public List<LoteResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public LoteResponseDTO buscarPorId(Long id) {
        Lote lote = repository.findById(id)
                .orElseThrow(() -> new LoteNaoEncontradoException(id));
        return mapper.toResponse(lote);
    }
}
