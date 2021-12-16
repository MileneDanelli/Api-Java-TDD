package api.tdd.dtos;

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
}
