package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para cadastrar um lote
// REGRAS: só validação de formato (hectares positivos, textos preenchidos)
// SITUAÇÃO: implementada
@Schema(description = "Dados para cadastrar um lote")
public record LoteRequestDTO(
        @Schema(description = "Área do lote em hectares", example = "10")
        @NotNull @Positive BigDecimal hectares,
        @Schema(description = "Endereço ou localização do lote", example = "Estrada Rural km 12, Zona Rural")
        @NotBlank @Size(max = 255) String endereco,
        @Schema(description = "Planta cultivada no lote", example = "Soja")
        @NotBlank @Size(max = 100) String planta) {
}
