package api.repository;

import api.model.Venda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: acesso ao banco para a tabela vendas
// REGRAS: nenhuma (a busca por planta é usada pela RN03 no VendaService)
// SITUAÇÃO: implementada
public interface VendaRepository extends JpaRepository<Venda, Long> {

    // busca as vendas já feitas da planta informada
    List<Venda> findByPlantaIgnoreCase(String planta);
}
