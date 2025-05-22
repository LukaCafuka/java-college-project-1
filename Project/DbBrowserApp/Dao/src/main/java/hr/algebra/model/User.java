/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author bubif
 */
public class User {
    private int id;
    private String userName;
    private String password;
    private int userRoleId;
    
    private static User instance;
    
    public void setUserInstance(User user) {
        if (instance == null) {
            instance = new User(user.getId(), user.getUserName(), user.getPassword(), user.getUserRoleId());
        }
    }
    
    public User getUserInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public User(int id, String userName, String password, int userRoleId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userRoleId = userRoleId;
    }

    public User(String userName, String password, int userRoleId) {
        this.userName = userName;
        this.password = password;
        this.userRoleId = userRoleId;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    
    
}
