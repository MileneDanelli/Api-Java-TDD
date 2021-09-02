package api.tdd.controllers;

import api.tdd.dtos.ListaAnimeDTO;
import api.tdd.dtos.NovoAnimeDTO;
import api.tdd.models.Anime;
import api.tdd.services.AnimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animes")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ListaAnimeDTO> visualizar(){
        return animeService.VisualizarAnime()
                .stream()
                .map(this::toListaAnimeDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anime cadastrar(@Valid @RequestBody NovoAnimeDTO animeDTO){
        return animeService.CadastrarAnime(animeDTO.converteModelo());
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Anime editar(@Valid @RequestBody Anime anime, @PathVariable("id") Long id){
        return animeService.EditarAnime(id, anime);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id") Long id){
        return animeService.DeletarAnime(id);
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Anime buscaAnimePorId(@PathVariable("id") Long id){
        return animeService.BuscarAnimePorId(id);
    }

    private ListaAnimeDTO toListaAnimeDTO(Anime anime){
        return modelMapper.map(anime, ListaAnimeDTO.class);
    }
}
