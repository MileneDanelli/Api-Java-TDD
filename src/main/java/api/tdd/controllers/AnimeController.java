package api.tdd.controllers;

import api.tdd.models.Anime;
import api.tdd.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animes")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findAnime(@PathVariable Long id) {

        if(id < 0) {
            return ResponseEntity.badRequest().build();
        }

        Anime anime = this.animeService.findAnime(id);

        if(anime == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(anime);
    }
}
