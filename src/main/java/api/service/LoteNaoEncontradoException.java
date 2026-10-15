package api.service;

// CAMADA: Service
// RESPONSABILIDADE: avisar que o lote buscado pelo id não existe (vira 404)
// REGRAS: filha de RecursoNaoEncontradoException, lançada pelo service quando o id não existe
// SITUAÇÃO: implementada
public class LoteNaoEncontradoException extends RecursoNaoEncontradoException {

    public LoteNaoEncontradoException(Long id) {
        super("Lote não encontrado: " + id);
    }
}
