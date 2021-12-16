package api.tdd.controllers;

import api.tdd.dtos.ListaTipoDTO;
import api.tdd.dtos.NovoTipoDTO;
import api.tdd.models.Tipo;
import api.tdd.services.TipoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/tipos")
public class TipoController {

        @Autowired
        private TipoService tipoService;

        @Autowired
        private ModelMapper modelMapper;

        @GetMapping
        public List<ListaTipoDTO> visualizar(){
            return tipoService.VisualizarTipo()
                    .stream()
                    .map(this::toListaTipoDTO)
                    .collect(Collectors.toList());
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Tipo cadastrar(@Valid NovoTipoDTO tipoDTO){
            return tipoService.CadastrarTipo(tipoDTO.converteModelo());
        }

        private ListaTipoDTO toListaTipoDTO(Tipo tipo){
            return modelMapper.map(tipo, ListaTipoDTO.class);
        }
}
