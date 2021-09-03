package api.tdd.controller;

import api.tdd.controllers.AnimeController;
import api.tdd.models.Anime;
import api.tdd.repositories.AnimeRepository;
import api.tdd.services.AnimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import net.minidev.json.JSONUtil;
import org.h2.util.geometry.GeoJsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    public void deveRetornarNaoEncontrado_QuandoBuscarAnime() throws Exception {
//        when(this.animeService.BuscarAnimePorId(1L)).thenReturn(new Anime(1L, "Mirai Nikki", "Sem Descrição"));

        mockMvc.perform(get("/animes/" + 5)
        ).andExpect(status().isNotFound());
    }
//
//    @Test
//    public void deveRetornarBadRequest_QuandoBuscarAnime() {
//        RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/animes/{id}", -1L).then().statusCode(HttpStatus.BAD_REQUEST.value());
//
//        verify(this.animeService, never()).BuscarAnimePorId(-1L);
//    }

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
