package api.tdd.dtos;

import api.tdd.models.Contato;
import lombok.Data;

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
