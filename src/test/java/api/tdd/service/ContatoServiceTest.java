package api.tdd.service;

import api.tdd.Excessoes.ExcessaoJaExistente;
import api.tdd.models.Contato;
import api.tdd.repositories.ContatoRepository;
import api.tdd.services.ContatoService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContatoServiceTest {

//    @Mock
//    private ContatoRepository contatoRepository;
//
//    private ContatoService contatoService;
//
//    @BeforeEach
//    void setUp() {
//        contatoService = new ContatoService(contatoRepository);
//    }
//
//    @Test
//    public void deveRetornarSucesso_QuandoCadastrarContato() {
//        Contato contato = Contato.builder()
//                .id(1L)
//                .nome("mi")
//                .email("mi@gmail.com")
//                .telefone("712371237")
//                .build();
//
//        Mockito.doReturn(contato).when(contatoRepository).save(any(Contato.class));
//
//        var result = contatoService.CadastrarContato(contato);
//
//        Assertions.assertThat(result)
//                .isNotNull();
//        Assertions.assertThat(result.getEmail())
//                .isEqualTo(contato.getEmail());
//    }
//
//    @Test
//    public void retornaExcessaoSeEmailJaExiste() {
//        Contato contato = Contato.builder()
//                .id(1L)
//                .nome("mi")
//                .email("mi@gmail.com")
//                .telefone("712371237")
//                .build();
//
//        Mockito.when(contatoRepository.findContatoByEmail(any())).thenReturn(Optional.of(contato));
//
//        Assertions.assertThatThrownBy(() -> contatoService.validacaoEmail("mi@gmail.com")).isInstanceOf(ExcessaoJaExistente.class);
//    }
}
