package api.tdd.service;

import api.tdd.models.Anime;
import api.tdd.repositories.AnimeRepository;
import api.tdd.services.AnimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnimeServiceTest {
    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepository;

    @Test
    public void deveRetornarSucesso_AoSalvarAnime() {
        Anime anime = new Anime();
        anime.setId(2L);
        anime.setNome("Teste");
        anime.setDescricao("Teste");

        animeService.CadastrarAnime(anime);

        verify(animeRepository, times(1)).save(anime);
    }

    @Test
    public void deveRetornarSucesso_AoEditarAnime() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setNome("Teste");
        anime.setDescricao("Teste");

        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        animeService.EditarAnime(1L, anime);

        verify(animeRepository, times(1)).findById(1L);
    }

    @Test
    public void deveRetornarSucesso_AoBuscarAnime() {
        animeService.VisualizarAnime();
        verify(animeRepository, times(1)).findAll();
    }

    @Test
    public void deveRetornarSucesso_AoBuscarAnimePorId() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setNome("Teste");
        anime.setDescricao("Teste");

        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        animeService.BuscarAnimePorId(1L);

        verify(animeRepository, times(1)).findById(1L);
    }

    @Test
    public void deveRetornarSucesso_AoDeletarAnimePorId() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setNome("Teste");
        anime.setDescricao("Teste");

        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));
        when(animeRepository.existsById(1L)).thenReturn(true);

        animeService.DeletarAnime(anime.getId());

        verify(animeRepository, times(1)).deleteById(anime.getId());
    }
}
