package api.tdd.dtos;

import lombok.Data;
import java.io.Serializable;

@Data
public class ListaAnimeDTO implements Serializable {
    private Long id;
    private String nome;
    private String descricao;
}
