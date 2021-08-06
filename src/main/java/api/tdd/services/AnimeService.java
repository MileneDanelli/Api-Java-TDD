package api.tdd.services;

import api.tdd.models.Anime;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {
    public Anime findAnime(Long id) {
        if(id > 100) {
            return null;
        }

        return new Anime(
                id,
                "Mirai Nikki",
                "Anime de Horror"
        );
    }
}
