package api.tdd.services;

import api.tdd.Excessoes.ExcessaoJaExistente;
import api.tdd.models.Produto;
import api.tdd.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public void validacaoNome(String nome){
        var produto = produtoRepository.findProdutoByNome(nome);
        if(produto.isPresent()){
            throw new ExcessaoJaExistente("Cadastre um Nome diferente!");
        }
    }

    public Produto BuscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Produto não Existe. id: " + id)
        );
    }

    public List<Produto> VisualizarProduto(){
        return produtoRepository.findAll();
    }

    public Produto CadastrarProduto(Produto produto) {
        validacaoNome(produto.getNome());
        return produtoRepository.save(produto);
    }

    public String DeletarProduto(Long id) {
        boolean existe = produtoRepository.existsById(id);
        if(!existe){
            throw new IllegalStateException("Este Produto não Existe. id: " + id);
        }
        produtoRepository.deleteById(id);
        return "Produto deletado com sucesso!";
    }

    @Transactional
    public Produto EditarProduto(Long id, Produto produto1) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Este Produto não Existe. id: " + id)
                );
        if(produto1.getNome() != null && !Objects.equals(produto.getNome(), produto1.getNome())){
            produto.setNome(produto1.getNome());
        }
        return produto;
    }
}
