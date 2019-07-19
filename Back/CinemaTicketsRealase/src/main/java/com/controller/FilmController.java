package com.controller;
import com.fasterxml.jackson.annotation.JsonView;
import com.models.*;
import com.service.FilmService;
import com.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    ///=================================Film========================
    @PostMapping("")
    public Long saveFilm(@RequestBody Film film){
        return filmService.saveFilm(film);
    }

    @PutMapping("")
    public Long updateFilm(@RequestBody Film film){
        return filmService.updateFilmById(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable Long id){
    filmService.deleteFilmById(id);
    }
    @JsonView(Views.Internal.class)
    @GetMapping("/{id}")
    public Film get(@PathVariable Long id){
        return filmService.findFilmById(id);
    }

    @JsonView(Views.Internal.class)
    @GetMapping("")
    public List <Film> getAll(){
        return filmService.findAllFilm();
    }

    ///=================================TypeFilm========================
    @JsonView(Views.Internal.class)
    @GetMapping("/type")
    public List<TypeFilm> getType(){
        return filmService.findAllTypeFilm();
    }
    @PutMapping("/type")
    public Long update(@RequestBody TypeFilm typeFilm){
       return filmService.updateTypeFilm(typeFilm);
    }

    ///=================================Genre========================
    @GetMapping("/genre")
    @JsonView(Views.Internal.class)
    public List<Genre> getGenre(){
        return filmService.findAllGenre();
    }

    @PutMapping("/genre")
    public Long update(@RequestBody Genre genre){
        return filmService.updateGenre(genre);
    }

    ///=================================LimitAge========================
    @GetMapping("/limitAge")
    @JsonView(Views.Internal.class)
    public List<LimitAge> getLimitage(){
        return filmService.findAllLimitAge();
    }

    @PutMapping("/limitAge")
    public Long updateLimitAge(@RequestBody LimitAge limitAge){
        return filmService.updateLimitAge(limitAge);
    }

    ///=================================Rating========================
    @JsonView(Views.Internal.class)
    @GetMapping("/rating")
    public List <Rating> getRating(){
        return filmService.findAllRating();
    }

    @PutMapping("/rating")
    public Long updateRating(@RequestBody Rating rating){
        return filmService.updateRating(rating);
    }

}
