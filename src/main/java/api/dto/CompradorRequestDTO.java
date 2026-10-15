package api.dto;

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
public record CompradorRequestDTO(
        @NotBlank @CNPJ(message = "CNPJ inválido") String cnpj,
        @NotBlank @Size(max = 255) String endereco,
        @NotBlank @Size(max = 100) String plantaPreco,
        @NotNull @Positive BigDecimal preco) {
}
