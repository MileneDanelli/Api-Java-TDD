package api.tdd.services;

import api.tdd.models.Tipo;
import api.tdd.repositories.TipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoService {
    private final TipoRepository tipoRepository;

    public List<Tipo> VisualizarTipo(){
        return tipoRepository.findAll();
    }

    public Tipo CadastrarTipo(Tipo tipo) {
        return tipoRepository.save(tipo);
    }
}
