package api.service;

// CAMADA: Service
// RESPONSABILIDADE: avisar que o comprador buscado pelo id não existe (vira 404)
// REGRAS: filha de RecursoNaoEncontradoException, lançada pelo service quando o id não existe
// SITUAÇÃO: implementada
public class CompradorNaoEncontradoException extends RecursoNaoEncontradoException {

    public CompradorNaoEncontradoException(Long id) {
        super("Comprador não encontrado: " + id);
    }
}
