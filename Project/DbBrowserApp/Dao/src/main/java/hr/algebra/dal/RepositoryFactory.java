/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.dal.sql.SqlRepository;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author bubif
 */
public class RepositoryFactory {
    private static Repository repository;
    private static final Properties PROPERTIES = new Properties();
    private static final String PATH = "/config/repository.properties";
    private static final String CLASS_NAME = "CLASS_NAME";
    
    static {
        try (InputStream is = RepositoryFactory.class.getResourceAsStream(PATH)) {
            PROPERTIES.load(is);
            
            repository = (Repository) Class.forName(PROPERTIES.getProperty(CLASS_NAME))
                    .getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private RepositoryFactory() {};
    
    public static Repository getRepository() throws Exception {
        if (repository == null) {
            repository = new SqlRepository();

        }
        return repository;
    }
    
}
