package api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

// CAMADA: Model
// RESPONSABILIDADE: representar a tabela insumos (adubo, semente etc.)
// REGRAS: nenhuma
// SITUAÇÃO: implementada
@Entity
@Table(name = "insumos")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_item", nullable = false, unique = true, length = 100)
    private String nomeItem;

    @Column(nullable = false)
    private BigDecimal quantidade;

    protected Insumo() {
    }

    public Insumo(String nomeItem, BigDecimal quantidade) {
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
}
