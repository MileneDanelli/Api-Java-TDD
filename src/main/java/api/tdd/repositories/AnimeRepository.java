package api.tdd.repositories;

import api.tdd.models.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Optional<Anime> findTipoProdutoByNome(String nome);
}
