package api.tdd.services;

import api.tdd.Excessoes.ExcessaoJaExistente;
import api.tdd.models.Contato;
import api.tdd.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    private final ContatoRepository contatoRepository;

    public void validacaoEmail(String email){
        var contato = contatoRepository.findContatoByEmail(email);
        if(contato.isPresent()){
            throw new ExcessaoJaExistente("Cadastre um Email diferente!");
        }
    }

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public Contato BuscarContatoPorId(Long id) {
        return contatoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Contato não Existe. id: " + id)
        );
    }

    public List<Contato> VisualizarContato(){
        return contatoRepository.findAll();
    }

    public Contato CadastrarContato(Contato contato) {
        validacaoEmail(contato.getEmail());
        return contatoRepository.save(contato);
    }

    public String DeletarContato(Long id) {
        boolean existe = contatoRepository.existsById(id);
        if(!existe){
            throw new IllegalStateException("Este Contato não Existe. id: " + id);
        }
        contatoRepository.deleteById(id);
        return "Contato deletado com sucesso!";
    }

    @Transactional
    public Contato EditarContato(Long id, Contato contato1) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Este Contato não Existe. id: " + id)
                );
        if(contato1.getNome() != null && !Objects.equals(contato.getNome(), contato1.getNome())){
            contato.setNome(contato1.getNome());
        }
        if (contato1.getEmail() != null && !Objects.equals(contato.getEmail(), contato1.getEmail())){
            contato.setEmail(contato1.getEmail());
        }
        if (contato1.getTelefone() != null && !Objects.equals(contato.getTelefone(), contato1.getTelefone())){
            contato.setTelefone(contato1.getTelefone());
        }
        return contato;
    }
}
