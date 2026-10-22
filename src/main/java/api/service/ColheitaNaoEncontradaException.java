package api.service;

// CAMADA: Service
// RESPONSABILIDADE: avisar que a colheita buscada pelo id não existe (vira 404)
// REGRAS: filha de RecursoNaoEncontradoException, lançada pelo service quando o id não existe
// SITUAÇÃO: implementada
public class ColheitaNaoEncontradaException extends RecursoNaoEncontradoException {

    public ColheitaNaoEncontradaException(Long id) {
        super("Colheita não encontrada: " + id);
    }
}
