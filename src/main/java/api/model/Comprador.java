package api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

// CAMADA: Model
// RESPONSABILIDADE: representar a tabela compradores (quem compra a produção)
// REGRAS: o preço combinado (preco) é a referência da RN02 na venda
// SITUAÇÃO: implementada
@Entity
@Table(name = "compradores")
public class Comprador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprador")
    private Long id;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private String endereco;

    @Column(name = "planta_preco", nullable = false)
    private String plantaPreco;

    @Column(nullable = false)
    private BigDecimal preco;

    protected Comprador() {
    }

    public Comprador(String cnpj, String endereco, String plantaPreco, BigDecimal preco) {
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.plantaPreco = plantaPreco;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPlantaPreco() {
        return plantaPreco;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
