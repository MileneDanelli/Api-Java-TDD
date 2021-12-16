package api.tdd.controllers;

import api.tdd.dtos.ListaFornecedorDTO;
import api.tdd.dtos.NovoFornecedorDTO;
import api.tdd.models.Fornecedor;
import api.tdd.services.FornecedorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ListaFornecedorDTO> visualizar(){
        return fornecedorService.VisualizarFornecedor()
                .stream()
                .map(this::toListaFornecedorDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor cadastrar(@Valid NovoFornecedorDTO fornecedorDTO){
        return fornecedorService.CadastrarFornecedor(fornecedorDTO.converteModelo());
    }

    private ListaFornecedorDTO toListaFornecedorDTO(Fornecedor fornecedor){
        return modelMapper.map(fornecedor, ListaFornecedorDTO.class);
    }
}
