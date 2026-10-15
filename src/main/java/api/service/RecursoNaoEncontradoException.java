package api.service;

// CAMADA: Service
// RESPONSABILIDADE: classe-mãe das exceções de "não encontrado" (vira 404 no ApiExceptionHandler)
// REGRAS: cada entidade tem a sua filha, lançada pelo service quando o id não existe
// SITUAÇÃO: implementada
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
