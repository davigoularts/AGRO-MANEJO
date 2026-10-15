package api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para registrar uma colheita
// REGRAS: só validação de formato (lote informado, quantidade positiva)
// SITUAÇÃO: implementada
public record ColheitaRequestDTO(
        @NotNull Long loteId,
        @NotNull @Positive BigDecimal quantidade) {
}
