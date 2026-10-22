package api.mapper;

import api.dto.InsumoRequestDTO;
import api.dto.InsumoResponseDTO;
import api.model.Insumo;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter InsumoRequestDTO em Insumo e Insumo em InsumoResponseDTO
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Component
public class InsumoMapper {

    public Insumo toEntity(InsumoRequestDTO dto) {
        return new Insumo(dto.nomeItem(), dto.quantidade());
    }

    public InsumoResponseDTO toResponse(Insumo insumo) {
        return new InsumoResponseDTO(insumo.getId(), insumo.getNomeItem(), insumo.getQuantidade());
    }
}
