package api.tdd.services;

import api.tdd.Excessoes.ExcessaoJaExistente;
import api.tdd.models.Produto;
import api.tdd.repositories.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveRetornarSucesso_QuandoCadastrarProduto() {
        Produto produto = Produto.builder()
                .id(1L)
                .nome("mi")
                .qtd(1)
                .compra(BigDecimal.valueOf(199))
                .venda(BigDecimal.valueOf(189))
                .build();

        Mockito.doReturn(produto).when(produtoRepository).save(any(Produto.class));

        var result = produtoService.CadastrarProduto(produto);

        Assertions.assertThat(result)
                .isNotNull();
        Assertions.assertThat(result.getNome())
                .isEqualTo(produto.getNome());
    }

    @Test
    void retornaExcessaoSeNomeDoProdutoJaExiste() {
        Produto produto = Produto.builder()
                .id(1L)
                .nome("mi")
                .qtd(1)
                .compra(BigDecimal.valueOf(199))
                .venda(BigDecimal.valueOf(189))
                .build();

        Mockito.when(produtoRepository.findProdutoByNome(any())).thenReturn(Optional.of(produto));

        Assertions.assertThatThrownBy(() -> produtoService.validacaoNome("mi")).isInstanceOf(ExcessaoJaExistente.class);
    }
}