package api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de uma venda
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public record VendaResponseDTO(Long id, Long compradorId, String planta, BigDecimal quantidade,
                               BigDecimal preco, LocalDate data) {
}
