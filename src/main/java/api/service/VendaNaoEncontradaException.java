package api.service;

// CAMADA: Service
// RESPONSABILIDADE: avisar que a venda buscada pelo id não existe (vira 404)
// REGRAS: filha de RecursoNaoEncontradoException, lançada pelo service quando o id não existe
// SITUAÇÃO: implementada
public class VendaNaoEncontradaException extends RecursoNaoEncontradoException {

    public VendaNaoEncontradaException(Long id) {
        super("Venda não encontrada: " + id);
    }
}
