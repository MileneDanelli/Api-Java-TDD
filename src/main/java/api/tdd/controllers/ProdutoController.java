package api.tdd.controllers;

import api.tdd.dtos.ListaProdutoDTO;
import api.tdd.dtos.NovoProdutoDTO;
import api.tdd.models.Produto;
import api.tdd.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ListaProdutoDTO> visualizar(){
        return produtoService.VisualizarProduto()
                .stream()
                .map(this::toListaProdutoDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@Valid NovoProdutoDTO produtoDTO){
        return produtoService.CadastrarProduto(produtoDTO.converteModelo());
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto editar(@Valid Produto produto, @PathVariable("id") Long id){
        return produtoService.EditarProduto(id, produto);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id") Long id){
        return produtoService.DeletarProduto(id);
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto buscaProdutoPorId(@PathVariable("id") Long id){
        return produtoService.BuscarProdutoPorId(id);
    }

    private ListaProdutoDTO toListaProdutoDTO(Produto produto){
        return modelMapper.map(produto, ListaProdutoDTO.class);
    }
}
