package api.dto;

import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de uma colheita
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public record ColheitaResponseDTO(Long id, Long loteId, BigDecimal quantidade) {
}
