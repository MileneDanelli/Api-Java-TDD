package api.tdd.dtos;

import api.tdd.models.Contato;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
public class NovoContatoDTO {
    private String nome;
    private String email;
    private String telefone;

    public Contato converteModelo(){
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        return contato;
    }
}
