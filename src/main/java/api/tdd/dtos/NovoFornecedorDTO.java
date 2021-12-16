package api.tdd.dtos;

import api.tdd.models.Fornecedor;
import lombok.Data;

@Data
public class NovoFornecedorDTO {
    private String nome;
    private String telefone;

    public Fornecedor converteModelo(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setTelefone(telefone);
        return fornecedor;
    }
}
