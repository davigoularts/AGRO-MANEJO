package api.repository;

import api.model.Colheita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: acesso ao banco para a tabela colheitas
// REGRAS: nenhuma (a busca por planta é usada pela RN03 no VendaService)
// SITUAÇÃO: implementada
public interface ColheitaRepository extends JpaRepository<Colheita, Long> {

    // busca as colheitas cujo lote plantou a planta informada
    List<Colheita> findByLotePlantaIgnoreCase(String planta);
}
