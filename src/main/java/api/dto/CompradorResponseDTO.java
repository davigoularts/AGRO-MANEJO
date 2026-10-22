package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de um comprador
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Schema(description = "Um comprador cadastrado")
public record CompradorResponseDTO(
        @Schema(description = "Id do comprador", example = "1")
        Long id,
        @Schema(description = "CNPJ, só com números", example = "11222333000181")
        String cnpj,
        @Schema(description = "Endereço do comprador", example = "Av. Industrial, 1000, Distrito Industrial")
        String endereco,
        @Schema(description = "Planta para a qual o preço foi combinado", example = "Soja")
        String plantaPreco,
        @Schema(description = "Preço combinado por saca", example = "120.00")
        BigDecimal preco) {
}
