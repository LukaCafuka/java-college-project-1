/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;
import hr.algebra.model.Category;
import hr.algebra.model.Image;
import hr.algebra.model.Person;
import hr.algebra.model.Photographer;
import hr.algebra.model.User;
import hr.algebra.model.Writer;
import java.util.List;
import java.util.Optional;

public interface Repository {
    
    /* IMAGE CRUD*/
    int createImage(Image image) throws Exception;
    void createImages(List<Image> images) throws Exception;
    void updateImage(int id, Image image) throws Exception;
    void deleteImage(int id) throws Exception;
    Optional<Image> selectImage(int id) throws Exception;
    List<Image> selectImages() throws Exception;
    
    /* PHOTOGRAPHER CRUD */
    int createPhotographer(Photographer photographer) throws Exception;
    void createPhotographers(List<Photographer> photographers) throws Exception;
    void updatePhotographer(int id, Photographer photographer) throws Exception;
    void deletePhotographer(int id) throws Exception;
    Optional<Photographer> selectPhotographer(int id) throws Exception;
    List<Photographer> selectPhotographers() throws Exception;
    
    /* WRITER CRUD */
    int createWriter(Writer writer) throws Exception;
    void createWriters(List<Writer> writers) throws Exception;
    void updateWriter(int id, Writer writer) throws Exception;
    void deleteWriter(int id) throws Exception;
    Optional<Writer> selectWriter(int id) throws Exception;
    List<Writer> selectWriters() throws Exception;
    
    /* CATEGORY CRUD */
    int createCategory(Category category) throws Exception;
    void createCategories(List<Category> categories) throws Exception;
    void updateCategory(int id, Category category) throws Exception;
    void deleteCategory(int id) throws Exception;
    Optional<Category> selectCategory(int id) throws Exception;
    List<Category> selectCategories() throws Exception;
    
    /* USER CRUD */
    int createUser(User user) throws Exception;
    int findUser(String username, String password) throws Exception;
    void deleteUser(int id) throws Exception;
    Optional<User> selectUser(int id) throws Exception;
    List<User> selectUsers() throws Exception;
    
    /* DELETE ALL DATA */
    void deleteAllData() throws Exception;
    
}
