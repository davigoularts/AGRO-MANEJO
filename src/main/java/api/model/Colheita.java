package api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

// CAMADA: Model
// RESPONSABILIDADE: representar a tabela colheitas (quanto foi colhido de um lote)
// REGRAS: nenhuma (as regras ficam no ColheitaService)
// SITUAÇÃO: implementada
@Entity
@Table(name = "colheitas")
public class Colheita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @Column(nullable = false)
    private BigDecimal quantidade;

    protected Colheita() {
    }

    public Colheita(Lote lote, BigDecimal quantidade) {
        this.lote = lote;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Lote getLote() {
        return lote;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
}
