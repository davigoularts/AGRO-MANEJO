package api.mapper;

import api.dto.LoteRequestDTO;
import api.dto.LoteResponseDTO;
import api.model.Lote;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter LoteRequestDTO em Lote e Lote em LoteResponseDTO
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Component
public class LoteMapper {

    public Lote toEntity(LoteRequestDTO dto) {
        return new Lote(dto.hectares(), dto.endereco(), dto.planta());
    }

    public LoteResponseDTO toResponse(Lote lote) {
        return new LoteResponseDTO(lote.getId(), lote.getHectares(), lote.getEndereco(), lote.getPlanta());
    }
}
