package com.service;
import com.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilmService {

    Long saveFilm(Film film);//
    void deleteFilmById(Long id);//
    Long updateFilmById(Film film);
    Film findFilmById(Long id);//
    List <Film> findFilmsByVars(String name,
                                String genre,
                                String typeFilm,
                                Double rating,
                                Integer age);
    void updateGRLT(Film film);
    List <Film> findAllByRatingId(Long id);
    List <Film> findAllByGenreId(Long id);
    List <Film> findAllByTypeFIlmId(Long id);
    List <Film> findAllByLimitAgeId(Long id);

    Long updateGenre(Genre genre);
    List<Genre> findAllGenre();

    Long updateTypeFilm(TypeFilm typeFilm);
    List<TypeFilm> findAllTypeFilm();

    Long updateLimitAge(LimitAge limitAge);
    List <LimitAge> findAllLimitAge();

    Long updateRating(Rating rating);
    List <Rating> findAllRating();

    Long loadImage(MultipartFile file, Image image) throws IOException;

    Image getImage(Long id);
}
