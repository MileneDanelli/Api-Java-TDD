package api.tdd.dtos;

import api.tdd.models.Anime;
import lombok.Data;

@Data
public class NovoAnimeDTO {
    private String nome;
    private String descricao;

    public Anime converteModelo(){
        Anime anime = new Anime();
        anime.setNome(nome);
        anime.setDescricao(descricao);
        return anime;
    }
}
