package api.tdd.services;

import api.tdd.models.Fornecedor;
import api.tdd.repositories.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> VisualizarFornecedor(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor CadastrarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
}
