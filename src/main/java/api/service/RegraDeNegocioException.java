package api.service;

// CAMADA: Service
// RESPONSABILIDADE: avisar que uma regra de negócio foi violada (vira 400 no ApiExceptionHandler)
// REGRAS: a mensagem diz qual regra barrou o pedido (ex.: "RN03: estoque insuficiente...")
// SITUAÇÃO: implementada
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
