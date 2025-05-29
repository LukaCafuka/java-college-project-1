/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Category;
import hr.algebra.model.Image;
import hr.algebra.model.Person;
import hr.algebra.model.Photographer;
import hr.algebra.model.User;
import hr.algebra.model.Writer;
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
    
    /* ID CONSTANTS */
    private static final String ID_IMAGE = "IDImage";
    private static final String ID_PHOTOGRAPHER = "IDPhotographer";
    private static final String ID_WRITER = "IDWriter";
    private static final String ID_CATEGORY = "IDCategory";
    private static final String ID_USER = "IDUser";
    
    /* ATTRIBUTE CONSTANTS */
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String USER_NAME = "UserName";
    private static final String PASSWORD = "Password";
    private static final String USER_ROLE_ID = "UserRoleID";
    private static final String NAME = "Name";

    /* PROCEDURE CONSTANTS */
    private static final String CREATE_IMAGE = "{ CALL createImage (?,?,?,?,?,?) }";
    private static final String UPDATE_IMAGE = "{ CALL updateImage (?,?,?,?,?,?) }";
    private static final String DELETE_IMAGE = "{ CALL deleteImage (?) }";
    private static final String SELECT_IMAGE = "{ CALL selectImage (?) }";
    private static final String SELECT_IMAGES = "{ CALL selectImages }";
    private static final String SET_IMAGE_PHOTOGRAPGER = "{ CALL setImagePhotographer (?,?) }";
    private static final String SET_IMAGE_WRITER = "{ CALL setImageWriter (?,?) }";
    private static final String SET_IMAGE_CATEGORY = "{ CALL setImageCategory (?,?) }";
    
    private static final String CREATE_PHOTOGRAPHER = "{ CALL createPhotographer (?,?,?) }";
    private static final String UPDATE_PHOTOGRAPHER = "{ CALL updatePhotographer (?,?,?) }";
    private static final String DELETE_PHOTOGRAPHER = "{ CALL deletePhotographer (?) }";
    private static final String SELECT_PHOTOGRAPHER = "{ CALL selectPhotographer (?) }";
    private static final String SELECT_PHOTOGRAPHERS = "{ CALL selectPhotographers }";
    
    private static final String CREATE_WRITER = "{ CALL createWriter (?,?,?) }";
    private static final String UPDATE_WRITER = "{ CALL updateWriter (?,?,?) }";
    private static final String DELETE_WRITER = "{ CALL deleteWriter (?) }";
    private static final String SELECT_WRITER = "{ CALL selectWriter (?) }";
    private static final String SELECT_WRITERS = "{ CALL selectWriters }";
    
    private static final String CREATE_CATEGORY = "{ CALL createCategory (?,?) }";
    private static final String UPDATE_CATEGORY = "{ CALL updateCategory (?,?) }";
    private static final String DELETE_CATEGORY = "{ CALL deleteCategory (?) }";
    private static final String SELECT_CATEGORY = "{ CALL selectCategory (?) }";
    private static final String SELECT_CATEGORIES = "{ CALL selectCategories }";
    
    private static final String CREATE_USER = "{ CALL createUser (?,?,?) }";
    private static final String FIND_USER = "{ CALL findUser (?,?,?) }";
    private static final String DELETE_USER = "{ CALL deleteUser (?) }";
    private static final String SELECT_USER = "{ CALL selectUser (?) }";
    private static final String SELECT_USERS = "{ CALL selectUsers }";
    
    private static final String DELETE_ALL_DATA = "{ CALL deleteAllData }";
    
    /* DB IMPLEMENTATION */

    @Override
    public int createImage(Image image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_IMAGE)) {
            
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
        CallableStatement stmt = con.prepareCall(CREATE_IMAGE)) {
            
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
        CallableStatement stmt = con.prepareCall(UPDATE_IMAGE)) {
            
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
        CallableStatement stmt = con.prepareCall(DELETE_IMAGE)) {
            
            
            stmt.setInt(ID_IMAGE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Image> selectImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_IMAGE)) {
            
            
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
        CallableStatement stmt = con.prepareCall(SELECT_IMAGES)) {
            
            
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

    @Override
    public int createPhotographer(Photographer photographer) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_PHOTOGRAPHER)) {
            
            stmt.setString(FIRST_NAME, photographer.getFirstName());
            stmt.setString(LAST_NAME, photographer.getLastName());
            
            stmt.registerOutParameter(ID_PHOTOGRAPHER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_PHOTOGRAPHER);
        }
    }

    @Override
    public void createPhotographers(List<Photographer> photographers) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_PHOTOGRAPHER)) {
            
            for (Photographer photographer : photographers) {
                stmt.setString(FIRST_NAME, photographer.getFirstName());
                stmt.setString(LAST_NAME, photographer.getLastName());

                
                stmt.registerOutParameter(ID_PHOTOGRAPHER, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updatePhotographer(int id, Photographer photographer) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(UPDATE_PHOTOGRAPHER)) {
            
            stmt.setString(FIRST_NAME, photographer.getFirstName());
            stmt.setString(LAST_NAME, photographer.getLastName());
            
            stmt.setInt(ID_PHOTOGRAPHER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletePhotographer(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_PHOTOGRAPHER)) {
            
            
            stmt.setInt(ID_PHOTOGRAPHER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Photographer> selectPhotographer(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_PHOTOGRAPHER)) {
            
            
            stmt.setInt(ID_PHOTOGRAPHER, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Photographer(
                            rs.getInt(ID_PHOTOGRAPHER),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)
                        )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Photographer> selectPhotographers() throws Exception {
        List<Photographer> photographers = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_PHOTOGRAPHERS)) {
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    photographers.add(new Photographer(
                            rs.getInt(ID_PHOTOGRAPHER),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)
                        )
                    );
                }
            }
        }
        return photographers;
    }

    @Override
    public int createWriter(Writer writer) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_WRITER)) {
            
            stmt.setString(FIRST_NAME, writer.getFirstName());
            stmt.setString(LAST_NAME, writer.getLastName());
            
            stmt.registerOutParameter(ID_WRITER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_WRITER);
        }
    }

    @Override
    public void createWriters(List<Writer> writers) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_WRITER)) {
            
            for (Writer writer : writers) {
                stmt.setString(FIRST_NAME, writer.getFirstName());
                stmt.setString(LAST_NAME, writer.getLastName());

                
                stmt.registerOutParameter(ID_WRITER, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateWriter(int id, Writer writer) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(UPDATE_WRITER)) {
            
            stmt.setString(FIRST_NAME, writer.getFirstName());
            stmt.setString(LAST_NAME, writer.getLastName());
            
            stmt.setInt(ID_WRITER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteWriter(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_WRITER)) {
            
            
            stmt.setInt(ID_WRITER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Writer> selectWriter(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_WRITER)) {
            
            
            stmt.setInt(ID_WRITER, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Writer(
                            rs.getInt(ID_WRITER),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)
                        )
                    );
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Writer> selectWriters() throws Exception {
        List<Writer> writers = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_WRITERS)) {
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    writers.add(new Writer(
                            rs.getInt(ID_WRITER),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)
                        )
                    );
                }
            }
        }
        return writers;
    }

    @Override
    public int createCategory(Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_CATEGORY)) {
            
            stmt.setString(NAME, category.getName());
            
            stmt.registerOutParameter(ID_CATEGORY, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_CATEGORY);
        }
    }

    @Override
    public void createCategories(List<Category> categories) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_CATEGORY)) {
            
            for (Category category : categories) {
                stmt.setString(NAME, category.getName());

                
                stmt.registerOutParameter(ID_CATEGORY, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateCategory(int id, Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(UPDATE_CATEGORY)) {
            
            stmt.setString(NAME, category.getName());
            
            stmt.setInt(ID_CATEGORY, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCategory(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_CATEGORY)) {
            
            
            stmt.setInt(ID_CATEGORY, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Category> selectCategory(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_CATEGORY)) {
            
            
            stmt.setInt(ID_CATEGORY, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Category(
                            rs.getInt(ID_CATEGORY),
                            rs.getString(NAME)
                        )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Category> selectCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_CATEGORIES)) {
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt(ID_CATEGORY),
                            rs.getString(NAME)
                        )
                    );
                }
            }
        }
        return categories;
    }

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            
            stmt.setString(USER_NAME, user.getUserName());
            stmt.setString(PASSWORD, user.getPassword());
            
            stmt.registerOutParameter(ID_USER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            
            
            stmt.setInt(ID_USER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<User> selectUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_USER)) {
            
            
            stmt.setInt(ID_USER, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USER_NAME),
                            rs.getString(PASSWORD),
                            rs.getInt(USER_ROLE_ID)
                        )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> selectUsers() throws Exception {
        List<User> users = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SELECT_USERS)) {
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USER_NAME),
                            rs.getString(PASSWORD),
                            rs.getInt(USER_ROLE_ID)
                        )
                    );
                }
            }
        }
        return users;
    }

    @Override
    public void deleteAllData() throws Exception {
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(DELETE_ALL_DATA)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public int findUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(FIND_USER)) {
            
            stmt.setString(USER_NAME, username);
            stmt.setString(PASSWORD, password);
            
            stmt.registerOutParameter(ID_USER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void setImagePhotographer(int id, Image image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SET_IMAGE_PHOTOGRAPGER)) {
            
            stmt.setInt(ID_PHOTOGRAPHER, id);
            
            stmt.setInt(ID_IMAGE, image.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void setImageWriter(int id, Image image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SET_IMAGE_WRITER)) {
            
            stmt.setInt(ID_WRITER, id);
            
            stmt.setInt(ID_IMAGE, image.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void setImageCategory(int id, Image image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
        CallableStatement stmt = con.prepareCall(SET_IMAGE_CATEGORY)) {
            
            stmt.setInt(ID_CATEGORY, id);
            
            stmt.setInt(ID_IMAGE, image.getId());
            stmt.executeUpdate();
        }
    }
    
}
