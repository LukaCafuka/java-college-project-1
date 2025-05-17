/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;
import hr.algebra.model.Film;
import java.util.List;
import java.util.Optional;

public interface Repository {
    
    int createFilm(Film film) throws Exception;
    void createFilms(List<Film> films) throws Exception;
    void updateFilm(int id, Film data) throws Exception;
    void deleteFilm(int id) throws Exception;
    Optional<Film> selectFilm(int id) throws Exception;
    List<Film> selectFilms() throws Exception;
}
