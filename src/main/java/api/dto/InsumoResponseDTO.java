package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de um insumo
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Schema(description = "Um insumo cadastrado")
public record InsumoResponseDTO(
        @Schema(description = "Id do insumo", example = "1")
        Long id,
        @Schema(description = "Nome do insumo", example = "Adubo NPK 10-10-10")
        String nomeItem,
        @Schema(description = "Quantidade em estoque", example = "500")
        BigDecimal quantidade) {
}
