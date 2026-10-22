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
import java.time.LocalDate;

// CAMADA: Model
// RESPONSABILIDADE: representar a tabela vendas (venda da produção para um comprador)
// REGRAS: nenhuma (as regras ficam no VendaService)
// SITUAÇÃO: implementada
@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comprador")
    private Comprador comprador;

    @Column(nullable = false)
    private String planta;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private LocalDate data;

    protected Venda() {
    }

    public Venda(Comprador comprador, String planta, BigDecimal quantidade, BigDecimal preco, LocalDate data) {
        this.comprador = comprador;
        this.planta = planta;
        this.quantidade = quantidade;
        this.preco = preco;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public String getPlanta() {
        return planta;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public LocalDate getData() {
        return data;
    }
}
