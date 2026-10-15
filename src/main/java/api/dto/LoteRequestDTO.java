package api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para cadastrar um lote
// REGRAS: só validação de formato (hectares positivos, textos preenchidos)
// SITUAÇÃO: implementada
public record LoteRequestDTO(
        @NotNull @Positive BigDecimal hectares,
        @NotBlank @Size(max = 255) String endereco,
        @NotBlank @Size(max = 100) String planta) {
}
