package api.dto;

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
public record VendaRequestDTO(
        @NotNull Long compradorId,
        @NotBlank @Size(max = 100) String planta,
        @NotNull @Positive BigDecimal quantidade,
        @NotNull @Positive BigDecimal preco,
        @NotNull LocalDate data) {
}
