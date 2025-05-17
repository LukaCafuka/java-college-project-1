/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;
import hr.algebra.model.Image;
import java.util.List;
import java.util.Optional;

public interface Repository {
    
    int createImage(Image image) throws Exception;
    void createImages(List<Image> images) throws Exception;
    void updateImage(int id, Image image) throws Exception;
    void deleteImage(int id) throws Exception;
    Optional<Image> selectImage(int id) throws Exception;
    List<Image> selectImages() throws Exception;
}
