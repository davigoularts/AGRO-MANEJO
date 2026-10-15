package api.dto;

import java.math.BigDecimal;

// CAMADA: Dto
// RESPONSABILIDADE: dados de saída de um comprador
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public record CompradorResponseDTO(Long id, String cnpj, String endereco, String plantaPreco, BigDecimal preco) {
}
