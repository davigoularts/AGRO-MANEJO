package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de uma colheita
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Schema(description = "Uma colheita registrada")
public record ColheitaResponseDTO(
        @Schema(description = "Id da colheita", example = "1")
        Long id,
        @Schema(description = "Id do lote colhido", example = "1")
        Long loteId,
        @Schema(description = "Sacas colhidas", example = "800")
        BigDecimal quantidade,
        // Novo campo adicionado na resposta da API
        @Schema(description = "Nome da cultura/planta colhida", example = "Soja")
        String planta) {
}
