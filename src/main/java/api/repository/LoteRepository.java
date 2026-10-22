package api.repository;

import api.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: acesso ao banco para a tabela lotes
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public interface LoteRepository extends JpaRepository<Lote, Long> {
}
