package api.tdd.dtos;

import api.tdd.models.Produto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NovoProdutoDTO {
    private String nome;
    private Integer qtd;
    private BigDecimal compra;
    private BigDecimal venda;

    public Produto converteModelo(){
        Produto contato = new Produto();
        contato.setNome(nome);
        contato.setQtd(qtd);
        contato.setCompra(compra);
        contato.setVenda(venda);
        return contato;
    }
}
