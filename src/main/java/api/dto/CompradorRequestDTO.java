package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.br.CNPJ;

// CAMADA: Dto
// RESPONSABILIDADE: dados de entrada para cadastrar um comprador
// REGRAS: só validação de formato (CNPJ válido, só números, preço positivo)
// SITUAÇÃO: implementada
@Schema(description = "Dados para cadastrar um comprador")
public record CompradorRequestDTO(
        @Schema(description = "CNPJ do comprador, com ou sem pontuação", example = "11.222.333/0001-81")
        @NotBlank @CNPJ(message = "CNPJ inválido") String cnpj,
        @Schema(description = "Endereço do comprador", example = "Av. Industrial, 1000, Distrito Industrial")
        @NotBlank @Size(max = 255) String endereco,
        @Schema(description = "Planta para a qual o preço foi combinado", example = "Soja")
        @NotBlank @Size(max = 100) String plantaPreco,
        @Schema(description = "Preço combinado por saca", example = "120.00")
        @NotNull @Positive BigDecimal preco) {
}
