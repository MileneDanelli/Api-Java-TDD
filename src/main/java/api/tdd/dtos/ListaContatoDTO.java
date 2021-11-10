package api.tdd.dtos;

import lombok.Data;
import java.io.Serializable;

@Data
public class ListaContatoDTO implements Serializable {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
