package api.tdd.controller;

import api.tdd.controllers.AnimeController;
import api.tdd.models.Anime;
import api.tdd.services.AnimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class AnimeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //No teste de Controller eu só tenho que testar o controller, por isso uso o mock nos outros(repositorio e service)
    @Autowired
    private AnimeController animeController;

    @MockBean //ele cria uma implementacao falsa, porque nao pode ser o real para não interferir no teste.
    private AnimeService animeService;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.animeController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarAnime() {
        when(this.animeService.BuscarAnimePorId(1L)).thenReturn(new Anime(1L, "Mirai Nikki", "Sem Descrição"));

        RestAssuredMockMvc.given().accept(ContentType.JSON)
                .when().get("/animes/{id}", 1L) //quando chegar uma requisição get no /filmes
                .then().statusCode(HttpStatus.OK.value()); //então status 200
    }

    @Test
    public void deveRetornarSucesso_AoSalvarAnime() throws Exception {
        Anime anime = new Anime(1L, "teste", "teste");

        mockMvc.perform(post("/animes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(anime))
        ).andExpect(status().isCreated());
    }

    @Test
    public void deveRetornarSucesso_AoEditarAnime() throws Exception {
        Anime anime = new Anime(1L, "teste", "teste");

        mockMvc.perform(put("/animes/" + anime.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(anime))
        ).andExpect(status().isOk());
    }

    @Test
    public void deveRetornarSucesso_AoDeletarAnime() throws Exception {
        Anime anime = new Anime(1L, "teste", "teste");

        mockMvc.perform(delete("/animes/" + anime.getId())
        ).andExpect(status().isOk());
    }
}
