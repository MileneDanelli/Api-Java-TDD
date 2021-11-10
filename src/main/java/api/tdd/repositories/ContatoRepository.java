package api.tdd.repositories;

import api.tdd.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findTipoProdutoByNome(String nome);
}