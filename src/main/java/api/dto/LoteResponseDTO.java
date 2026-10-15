package api.dto;

import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de um lote
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public record LoteResponseDTO(Long id, BigDecimal hectares, String endereco, String planta) {
}
