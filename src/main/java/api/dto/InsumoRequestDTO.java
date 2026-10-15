package api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para cadastrar um insumo
// REGRAS: só validação de formato (nome preenchido, quantidade não negativa)
// SITUAÇÃO: implementada
public record InsumoRequestDTO(
        @NotBlank @Size(max = 100) String nomeItem,
        @NotNull @PositiveOrZero BigDecimal quantidade) {
}
