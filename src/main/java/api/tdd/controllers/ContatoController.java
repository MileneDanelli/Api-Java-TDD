package api.tdd.controllers;

import api.tdd.dtos.ListaContatoDTO;
import api.tdd.dtos.NovoContatoDTO;
import api.tdd.models.Contato;
import api.tdd.services.ContatoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ListaContatoDTO> visualizar(){
        return contatoService.VisualizarContato()
                .stream()
                .map(this::toListaContatoDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato cadastrar(@Valid NovoContatoDTO animeDTO){
        return contatoService.CadastrarContato(animeDTO.converteModelo());
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato editar(@Valid Contato contato, @PathVariable("id") Long id){
        return contatoService.EditarContato(id, contato);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id") Long id){
        return contatoService.DeletarContato(id);
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato buscaContatoPorId(@PathVariable("id") Long id){
        return contatoService.BuscarContatoPorId(id);
    }

    private ListaContatoDTO toListaContatoDTO(Contato contato){
        return modelMapper.map(contato, ListaContatoDTO.class);
    }
}
