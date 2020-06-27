package cn.cxd.model;

import java.util.Arrays;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:42 2020/3/23
 * @Modified By:
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String[] hobbies;

    public User() { }


    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hobbies=" + Arrays.toString(hobbies) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String username, String password, String[] hobbies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hobbies = hobbies;
    }
}
