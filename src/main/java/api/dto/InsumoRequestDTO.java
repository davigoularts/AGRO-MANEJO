package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para cadastrar um insumo
// REGRAS: só validação de formato (nome preenchido, quantidade não negativa)
// SITUAÇÃO: implementada
@Schema(description = "Dados para cadastrar um insumo")
public record InsumoRequestDTO(
        @Schema(description = "Nome do insumo, sem repetir um já cadastrado", example = "Adubo NPK 10-10-10")
        @NotBlank @Size(max = 100) String nomeItem,
        @Schema(description = "Quantidade em estoque", example = "500")
        @NotNull @PositiveOrZero BigDecimal quantidade) {
}
