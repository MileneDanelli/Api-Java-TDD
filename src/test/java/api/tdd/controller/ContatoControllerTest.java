package api.tdd.controller;

import api.tdd.controllers.ContatoController;
import api.tdd.models.Contato;
import api.tdd.services.ContatoService;
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
public class ContatoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //No teste de Controller eu só tenho que testar o controller, por isso uso o mock nos outros(repositorio e service)
    @Autowired
    private ContatoController contatoController;

    @MockBean //ele cria uma implementacao falsa, porque nao pode ser o real para não interferir no teste.
    private ContatoService contatoService;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.contatoController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarAnime() {
        when(this.contatoService.BuscarContatoPorId(1L)).thenReturn(new Contato(1L, "Mi", "mi@outlook.com", "45999289744"));

        RestAssuredMockMvc.given().accept(ContentType.JSON)
                .when().get("/contatos/{id}", 1L) //quando chegar uma requisição get no /filmes
                .then().statusCode(HttpStatus.OK.value()); //então status 200
    }
}
