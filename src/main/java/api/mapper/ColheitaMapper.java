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
        // Agora passamos também o nome da planta que vem do DTO:
        return new Colheita(lote, dto.quantidade(), dto.planta());
    }

    public ColheitaResponseDTO toResponse(Colheita colheita) {
        // Agora incluímos a planta no relatório de resposta enviado para a tela:
        return new ColheitaResponseDTO(colheita.getId(), colheita.getLote().getId(), colheita.getQuantidade(), colheita.getPlanta());
    }
}
