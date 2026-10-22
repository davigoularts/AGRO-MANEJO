package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

// CAMADA: Dto
// RESPONSABILIDADE: formato único de erro da API (todo 400 e 404 sai assim)
// REGRAS: nenhuma (montado pelo ApiExceptionHandler)
// SITUAÇÃO: implementada
@Schema(description = "Formato padrão de erro da API")
public record ErroDTO(
        @Schema(description = "Status HTTP", example = "404")
        int status,
        @Schema(description = "Nome do status HTTP", example = "Not Found")
        String erro,
        @Schema(description = "O que deu errado, em português", example = "Lote não encontrado: 999")
        String mensagem,
        @Schema(description = "Caminho da requisição", example = "/lotes/999")
        String caminho,
        @Schema(description = "Momento do erro", example = "2026-10-15T19:30:00")
        LocalDateTime timestamp,
        @Schema(description = "Erros de validação por campo (vazio quando não se aplica)")
        List<CampoErroDTO> campos) {
}
