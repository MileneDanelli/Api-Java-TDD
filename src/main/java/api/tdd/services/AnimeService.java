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

    public Anime BuscarAnimePorId(Long id) {
        return animeRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Produto não Existe. id: " + id)
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
    public Anime EditarAnime(Long id, Anime anime1) {
        Anime anime = animeRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Este Anime não Existe. id: " + id)
                );
        if(anime1.getNome() != null && !Objects.equals(anime.getNome(), anime1.getNome())){
            anime.setNome(anime1.getNome());
        }
        if (anime1.getDescricao() != null && !Objects.equals(anime.getDescricao(), anime1.getDescricao())){
            anime.setDescricao(anime1.getDescricao());
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
