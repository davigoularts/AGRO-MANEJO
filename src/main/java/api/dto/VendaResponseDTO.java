package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de uma venda
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Schema(description = "Uma venda registrada")
public record VendaResponseDTO(
        @Schema(description = "Id da venda", example = "1")
        Long id,
        @Schema(description = "Id do comprador", example = "1")
        Long compradorId,
        @Schema(description = "Planta vendida", example = "Soja")
        String planta,
        @Schema(description = "Sacas vendidas", example = "500")
        BigDecimal quantidade,
        @Schema(description = "Preço por saca praticado na venda", example = "125.00")
        BigDecimal preco,
        @Schema(description = "Data da venda", example = "2026-10-10")
        LocalDate data) {
}
