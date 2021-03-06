package com.controller;
import com.fasterxml.jackson.annotation.JsonView;
import com.models.*;
import com.service.CommentService;
import com.service.FilmService;
import com.view.Views;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/Film")
public class FilmController {
    private final FilmService filmService;
private final CommentService commentService;

    public FilmController(FilmService filmService, CommentService commentService) {
        this.filmService = filmService;
        this.commentService = commentService;
    }
    ///=================================Film========================
    @PostMapping("/save")
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

    @JsonView(Film.View.Public.class)
    @GetMapping("/{id}")
    public Film get(@PathVariable Long id){
        return filmService.findFilmById(id);
    }

    @JsonView(Film.View.Public.class)
    @GetMapping("")
    public List <Film> getAll(@RequestParam(name = "name",required = false) String name ,
                              @RequestParam(name = "limitAge" ,required = false) Integer limitAge,
                              @RequestParam(name = "rating" ,required = false) Double rating,
                              @RequestParam(name = "typeFilm" ,required = false) String typeFilm,
                              @RequestParam(name = "genre" ,required = false) String genre){
        return filmService.findFilmsByVars(name,genre,typeFilm,rating,limitAge);
    }
    @JsonView(Film.View.Public.class)
    @GetMapping("/all")
    public List <Film> getAllFilms(){
        return filmService.getAllFilms();
    }


    ///=================================TypeFilm========================
    @JsonView(Film.View.Public.class)
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



    @JsonView(Views.Internal.class)
    @PostMapping("/image/upload")
    public Long loadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) throws IOException {
        return filmService.loadImage(file, id);
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/image/get")
    public ResponseEntity<byte[]> getImageWithMediaType(@RequestParam("id") Long id) throws IOException {
        Film film = filmService.findFilmById(id);
        Image image1 = filmService.getImage( film.getImage().getId());
        InputStream targetStream = new ByteArrayInputStream(image1.getImage_array());
        return ResponseEntity.ok().header("content-type", image1.getType()).body(IOUtils.toByteArray(targetStream))  ;
    }

    @DeleteMapping("/image/delete/{id}")
    public void deleteImageById(@PathVariable Long id){
        filmService.deleteImage(id);
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/image/getAll")
    public List<Image> getAllImage(){
        return filmService.getAllImage();
    }

    @JsonView(Views.Internal.class)
    @PostMapping("/image/update")
    public Long loadImageUpd(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) throws IOException {
        return filmService.loadImageUpdate(file, id);
    }

    ///=================================Rating========================
    @JsonView(Views.Internal.class)
    @GetMapping("/rating_{id_film}")
    public Double getRatingFilm(@PathVariable Long id_film){
        return filmService.findFilmRating(id_film);
    }

    @PostMapping("/rating/{id_film},{rating:.+}")
    public void addRating(@PathVariable Long id_film, @AuthenticationPrincipal KinoUser kinoUser, @PathVariable Double rating){
        filmService.addRating(id_film, kinoUser, rating);
    }

@PostMapping("/comment/add/{id_film}")
    public void addComment(@AuthenticationPrincipal KinoUser kinoUser, @RequestBody Comment comment, @PathVariable(name = "id_film") Long id_film){
        commentService.addComment(kinoUser, comment, id_film);
}
@GetMapping("/comment/getAll/{id_film}")
    public List<Comment> getComments(@PathVariable(name = "id_film") Long id_film) {
        return commentService.getComments(id_film);
}
@DeleteMapping("/comment/delete/{id_comment}")
    public void deleteComment(@PathVariable(name = "id_comment") Long id_comment){
        commentService.deleteComment(id_comment);
}
@PutMapping("/comment/update")
    public Long updateComment(@RequestBody Comment comment){
        return commentService.updateComment(comment);
}
}
