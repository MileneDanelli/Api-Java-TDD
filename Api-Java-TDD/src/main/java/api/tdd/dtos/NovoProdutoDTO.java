package api.tdd.dtos;

import api.tdd.models.Fornecedor;
import api.tdd.models.Produto;
import api.tdd.models.Tipo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NovoProdutoDTO {
    private String nome;
    private Integer qtd;
    private BigDecimal compra;
    private BigDecimal venda;
    private Tipo tipo;
    private Fornecedor fornecedor;

    public Produto converteModelo(){
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQtd(qtd);
        produto.setCompra(compra);
        produto.setVenda(venda);
        produto.setTipo(tipo);
        produto.setFornecedor(fornecedor);
        return produto;
    }
}
