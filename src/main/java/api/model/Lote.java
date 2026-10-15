package api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

// CAMADA: Model
// RESPONSABILIDADE: representar a tabela lotes (talhão de plantio)
// REGRAS: nenhuma (hectares > 0 é validado no DTO e no banco)
// SITUAÇÃO: implementada
@Entity
@Table(name = "lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal hectares;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String planta;

    protected Lote() {
    }

    public Lote(BigDecimal hectares, String endereco, String planta) {
        this.hectares = hectares;
        this.endereco = endereco;
        this.planta = planta;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getHectares() {
        return hectares;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPlanta() {
        return planta;
    }
}
