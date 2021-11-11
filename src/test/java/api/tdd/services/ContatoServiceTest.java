package api.tdd.services;

import api.tdd.Excessoes.ExcessaoJaExistente;
import api.tdd.models.Contato;
import api.tdd.repositories.ContatoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ContatoServiceTest {
    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoService contatoService;

    @Test
    void deveRetornarSucesso_QuandoCadastrarContato() {
        Contato contato = Contato.builder()
                .id(1L)
                .nome("mi")
                .email("mi@gmail.com")
                .telefone("712371237")
                .build();

        Mockito.doReturn(contato).when(contatoRepository).save(any(Contato.class));

        var result = contatoService.CadastrarContato(contato);

        Assertions.assertThat(result)
                .isNotNull();
        Assertions.assertThat(result.getEmail())
                .isEqualTo(contato.getEmail());
    }

    @Test
    void retornaExcessaoSeEmailJaExiste() {
        Contato contato = Contato.builder()
                .id(1L)
                .nome("mi")
                .email("mi@gmail.com")
                .telefone("712371237")
                .build();

        Mockito.when(contatoRepository.findContatoByEmail(any())).thenReturn(Optional.of(contato));

        Assertions.assertThatThrownBy(() -> contatoService.validacaoEmail("mi@gmail.com")).isInstanceOf(ExcessaoJaExistente.class);
    }
}