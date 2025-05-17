/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Image;
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
    
    private static final String ID_IMAGE = "IDImage";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";

    private static final String CREATE_FILM = "{ CALL createImage (?,?,?,?,?,?) }";
    private static final String UPDATE_FILM = "{ CALL updateImage (?,?,?,?,?,?) }";
    private static final String DELETE_FILM = "{ CALL deleteImage (?) }";
    private static final String SELECT_FILM = "{ CALL selectImage (?) }";
    private static final String SELECT_FILMS = "{ CALL selectImages }";

    @Override
    public int createImage(Image image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_FILM)) {
            
            stmt.setString(TITLE, image.getTitle());
            stmt.setString(LINK, image.getLink());
            stmt.setString(DESCRIPTION, image.getDescription());
            stmt.setString(PICTURE_PATH, image.getPicturePath());
            stmt.setString(PUBLISHED_DATE, image.getPublishedDate().format(Image.DATE_FORMATTER));
            
            stmt.registerOutParameter(ID_IMAGE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_IMAGE);
        }

    }

    @Override
    public void createImages(List<Image> images) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_FILM)) {
            
            for (Image image : images) {
                stmt.setString(TITLE, image.getTitle());
                stmt.setString(LINK, image.getLink());
                stmt.setString(DESCRIPTION, image.getDescription());
                stmt.setString(PICTURE_PATH, image.getPicturePath());
                stmt.setString(PUBLISHED_DATE, image.getPublishedDate().format(Image.DATE_FORMATTER));
                
                stmt.registerOutParameter(ID_IMAGE, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateImage(int id, Image image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(UPDATE_FILM)) {
            
            stmt.setString(TITLE, image.getTitle());
            stmt.setString(LINK, image.getLink());
            stmt.setString(DESCRIPTION, image.getDescription());
            stmt.setString(PICTURE_PATH, image.getPicturePath());
            stmt.setString(PUBLISHED_DATE, image.getPublishedDate().format(Image.DATE_FORMATTER));
            
            stmt.setInt(ID_IMAGE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_FILM)) {
            
            
            stmt.setInt(ID_IMAGE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Image> selectImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_FILM)) {
            
            
            stmt.setInt(ID_IMAGE, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Image(
                            rs.getInt(ID_IMAGE),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE),
                                    Image.DATE_FORMATTER)

                        )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Image> selectImages() throws Exception {
        List<Image> images = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_FILMS)) {
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    images.add(new Image(
                            rs.getInt(ID_IMAGE),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE),
                                    Image.DATE_FORMATTER)

                        )
                    );
                }
            }
        }
        return images;
    }
    
}
