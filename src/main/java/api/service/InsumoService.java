package api.service;

import api.dto.InsumoRequestDTO;
import api.dto.InsumoResponseDTO;
import api.mapper.InsumoMapper;
import api.model.Insumo;
import api.repository.InsumoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// CAMADA: Service
// RESPONSABILIDADE: cadastrar, listar e buscar insumos
// REGRAS: não permite dois insumos com o mesmo nome (400); id inexistente retorna 404
// ROTAS ATENDIDAS: as de /insumos em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Service
public class InsumoService {

    private final InsumoRepository repository;
    private final InsumoMapper mapper;

    public InsumoService(InsumoRepository repository, InsumoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InsumoResponseDTO cadastrar(InsumoRequestDTO dto) {
        if (repository.existsByNomeItem(dto.nomeItem())) {
            throw new RegraDeNegocioException("Já existe um insumo com o nome " + dto.nomeItem());
        }
        Insumo insumo = repository.save(mapper.toEntity(dto));
        return mapper.toResponse(insumo);
    }

    public List<InsumoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public InsumoResponseDTO buscarPorId(Long id) {
        Insumo insumo = repository.findById(id)
                .orElseThrow(() -> new InsumoNaoEncontradoException(id));
        return mapper.toResponse(insumo);
    }
}
