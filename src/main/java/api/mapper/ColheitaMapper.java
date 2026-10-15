package api.mapper;

import api.dto.ColheitaRequestDTO;
import api.dto.ColheitaResponseDTO;
import api.model.Colheita;
import api.model.Lote;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter ColheitaRequestDTO em Colheita e Colheita em ColheitaResponseDTO
// REGRAS: nenhuma (o lote já chega buscado pelo Service)
// SITUAÇÃO: implementada
@Component
public class ColheitaMapper {

    public Colheita toEntity(ColheitaRequestDTO dto, Lote lote) {
        return new Colheita(lote, dto.quantidade());
    }

    public ColheitaResponseDTO toResponse(Colheita colheita) {
        return new ColheitaResponseDTO(colheita.getId(), colheita.getLote().getId(), colheita.getQuantidade());
    }
}
