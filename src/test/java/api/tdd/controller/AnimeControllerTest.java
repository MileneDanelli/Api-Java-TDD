package api.tdd.controller;

import api.tdd.controllers.AnimeController;
import api.tdd.models.Anime;
import api.tdd.services.AnimeService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.mockito.Mockito.*;

@WebMvcTest
public class AnimeControllerTest {

    @Autowired
    private AnimeController animeController;

    @MockBean //ele cria uma implementacao falsa, porque nao pode ser o real para não interferir no teste.
    private AnimeService animeService;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.animeController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarAnime() {

        when(this.animeService.findAnime(1L)).thenReturn(new Anime(1L, "Mirai Nikki", "Sem Descrição"));

        RestAssuredMockMvc.given().accept(ContentType.JSON)
                .when().get("/animes/{id}", 1L) //quando chegar uma requisição get no /filmes
                .then().statusCode(HttpStatus.OK.value()); //então status 200
    }

    @Test
    public void deveRetornarNaoEncontrado_QuandoBuscarAnime() {

        when(this.animeService.findAnime(5L)).thenReturn(null);

        RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/animes/{id}", 5L).then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarBadRequest_QuandoBuscarAnime() {

        RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/animes/{id}", -1L).then().statusCode(HttpStatus.BAD_REQUEST.value());

        verify(this.animeService, never()).findAnime(-1L);
    }
}
