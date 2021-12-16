package api.tdd.dtos;

import api.tdd.models.Tipo;
import lombok.Data;

@Data
public class NovoTipoDTO {
    private String nome;

    public Tipo converteModelo(){
        Tipo tipo = new Tipo();
        tipo.setNome(nome);
        return tipo;
    }
}
