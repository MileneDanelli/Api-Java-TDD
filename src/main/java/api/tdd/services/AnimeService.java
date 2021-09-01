package api.tdd.services;

import api.tdd.models.Anime;
import api.tdd.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimeService {
    @Autowired
    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime findAnime(Long id) {
        if(id > 100) {
            return null;
        }

        return new Anime(
                id,
                "Mirai Nikki",
                "Anime de Horror"
        );
    }

    public List<Anime> VisualizarAnime(){
        return animeRepository.findAll();
    }

    public Anime CadastrarAnime(Anime anime) {
        validacaoNome(anime.getNome());
        return animeRepository.save(anime);
    }

    public String DeletarAnime(Long id) {
        boolean existe = animeRepository.existsById(id);
        if(!existe){
            throw new IllegalStateException("Este Anime não Existe. id: " + id);
        }
        animeRepository.deleteById(id);
        return "Anime deletado com sucesso!";
    }

    @Transactional
    public Anime EditarAnime(Long id, Anime tipoProduto) {
        Anime anime = animeRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Este Anime não Existe. id: " + id)
                );
        if(tipoProduto.getNome() != null && !Objects.equals(anime.getNome(), tipoProduto.getNome())){
            anime.setNome(tipoProduto.getNome());
        }
        if (tipoProduto.getDescricao() != null && !Objects.equals(anime.getDescricao(), tipoProduto.getDescricao())){
            anime.setDescricao(tipoProduto.getDescricao());
        }
        return anime;
    }

    public void validacaoNome(String nome){
        Optional<Anime> AnimePorNome = animeRepository.findTipoProdutoByNome(nome);
        if(AnimePorNome.isPresent()){
            throw new IllegalStateException("Anime Já Existente");
        }
    }
}
