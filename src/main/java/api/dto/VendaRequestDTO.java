package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para registrar uma venda
// REGRAS: só validação de formato (preço e quantidade positivos, data preenchida)
// SITUAÇÃO: implementada
@Schema(description = "Dados para registrar uma venda")
public record VendaRequestDTO(
        @Schema(description = "Id do comprador", example = "1")
        @NotNull Long compradorId,
        @Schema(description = "Planta vendida. RN02: precisa ser a planta negociada com o comprador", example = "Soja")
        @NotBlank @Size(max = 100) String planta,
        @Schema(description = "Sacas vendidas. RN03: não pode passar do estoque da planta", example = "500")
        @NotNull @Positive BigDecimal quantidade,
        @Schema(description = "Preço por saca. RN02: até 50% acima ou abaixo do preço combinado", example = "125.00")
        @NotNull @Positive BigDecimal preco,
        @Schema(description = "Data da venda, no formato yyyy-MM-dd", example = "2026-10-10")
        @NotNull LocalDate data) {
}
