package api.repository;

import api.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: acesso ao banco para a tabela insumos
// REGRAS: nenhuma
// SITUAÇÃO: implementada
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    boolean existsByNomeItem(String nomeItem);
}
