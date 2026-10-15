package api.repository;

import api.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: acesso ao banco para a tabela compradores
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

    boolean existsByCnpj(String cnpj);
}
