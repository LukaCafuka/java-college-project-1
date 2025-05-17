/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Film;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;


public class SqlRepository implements Repository {
    
    private static final String ID_FILM = "IDFilm";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";

    private static final String CREATE_FILM = "{ CALL createFilm (?,?,?,?,?,?) }";
    private static final String UPDATE_FILM = "{ CALL updateFilm (?,?,?,?,?,?) }";
    private static final String DELETE_FILM = "{ CALL deleteFilm (?) }";
    private static final String SELECT_FILM = "{ CALL selectFilm (?) }";
    private static final String SELECT_FILMS = "{ CALL selectFilms }";

    @Override
    public int createFilm(Film film) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_FILM)) {
            
            stmt.setString(TITLE, film.getTitle());
            stmt.setString(LINK, film.getLink());
            stmt.setString(DESCRIPTION, film.getDescription());
            stmt.setString(PICTURE_PATH, film.getPicturePath());
            stmt.setString(PUBLISHED_DATE, film.getPublishedDate().format(Film.DATE_FORMATTER));
            
            stmt.registerOutParameter(ID_FILM, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_FILM);
        }

    }

    @Override
    public void createFilms(List<Film> films) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_FILM)) {
            
            for (Film film : films) {
                stmt.setString(TITLE, film.getTitle());
                stmt.setString(LINK, film.getLink());
                stmt.setString(DESCRIPTION, film.getDescription());
                stmt.setString(PICTURE_PATH, film.getPicturePath());
                stmt.setString(PUBLISHED_DATE, film.getPublishedDate().format(Film.DATE_FORMATTER));
                
                stmt.registerOutParameter(ID_FILM, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateFilm(int id, Film film) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(UPDATE_FILM)) {
            
            stmt.setString(TITLE, film.getTitle());
            stmt.setString(LINK, film.getLink());
            stmt.setString(DESCRIPTION, film.getDescription());
            stmt.setString(PICTURE_PATH, film.getPicturePath());
            stmt.setString(PUBLISHED_DATE, film.getPublishedDate().format(Film.DATE_FORMATTER));
            
            stmt.setInt(ID_FILM, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteFilm(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_FILM)) {
            
            
            stmt.setInt(ID_FILM, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Film> selectFilm(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_FILM)) {
            
            
            stmt.setInt(ID_FILM, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                        new Film(
                            rs.getInt(ID_FILM),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Film.DATE_FORMATTER)

                        )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Film> selectFilms() throws Exception {
        List<Film> films = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_FILMS)) {
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    films.add(
                        new Film(
                            rs.getInt(ID_FILM),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Film.DATE_FORMATTER)

                        )
                    );
                }
            }
        }
        return films;
    }
    
}
