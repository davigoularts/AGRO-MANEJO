package api.dto;

import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de um insumo
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public record InsumoResponseDTO(Long id, String nomeItem, BigDecimal quantidade) {
}
