package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// CAMADA: Dto
// RESPONSABILIDADE: um erro de validação de campo, dentro da lista "campos" do ErroDTO
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Schema(description = "Erro de validação de um campo do corpo da requisição")
public record CampoErroDTO(
        @Schema(description = "Nome do campo no JSON", example = "hectares")
        String campo,
        @Schema(description = "Mensagem da validação que falhou", example = "must be greater than 0")
        String mensagem) {
}
