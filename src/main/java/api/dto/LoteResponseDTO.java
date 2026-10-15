package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de um lote
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Schema(description = "Um lote de plantio")
public record LoteResponseDTO(
        @Schema(description = "Id do lote", example = "1")
        Long id,
        @Schema(description = "Área do lote em hectares", example = "10")
        BigDecimal hectares,
        @Schema(description = "Endereço ou localização do lote", example = "Estrada Rural km 12, Zona Rural")
        String endereco,
        @Schema(description = "Planta cultivada no lote", example = "Soja")
        String planta) {
}
