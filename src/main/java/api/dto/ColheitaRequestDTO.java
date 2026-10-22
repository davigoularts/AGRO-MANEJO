package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para registrar uma colheita
// REGRAS: só validação de formato (lote informado, quantidade positiva, planta informada)
// SITUAÇÃO: implementada
@Schema(description = "Dados para registrar uma colheita")
public record ColheitaRequestDTO(
        @Schema(description = "Id do lote colhido", example = "1")
        @NotNull Long loteId,

        @Schema(description = "Sacas colhidas. RN01: no máximo 100 sacas por hectare do lote", example = "800")
        @NotNull @Positive BigDecimal quantidade,

        // Novo campo adicionado para a documentação e validação:
        @Schema(description = "Nome da cultura/planta colhida", example = "Soja")
        @NotBlank String planta) {
}
