package api.tdd.dtos;

import api.tdd.models.Fornecedor;
import api.tdd.models.Tipo;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ListaProdutoDTO implements Serializable {
    private Long id;
    private String nome;
    private Integer qtd;
    private BigDecimal compra;
    private BigDecimal venda;
    private Fornecedor fornecedor;
    private Tipo tipo;
}
