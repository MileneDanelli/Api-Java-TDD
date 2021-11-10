package api.tdd.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Email(message = "Email Inválido")
    @Column(unique = true, nullable = false)
    private String email;

    private String telefone;
}
