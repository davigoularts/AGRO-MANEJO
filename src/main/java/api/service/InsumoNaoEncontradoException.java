package api.service;

// CAMADA: Service
// RESPONSABILIDADE: avisar que o insumo buscado pelo id não existe (vira 404)
// REGRAS: filha de RecursoNaoEncontradoException, lançada pelo service quando o id não existe
// SITUAÇÃO: implementada
public class InsumoNaoEncontradoException extends RecursoNaoEncontradoException {

    public InsumoNaoEncontradoException(Long id) {
        super("Insumo não encontrado: " + id);
    }
}
