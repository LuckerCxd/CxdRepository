package cn.cxd.model;

import java.io.Serializable;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:37 2020/3/19
 * @Modified By:
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private int kda;

    public User() { }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", kda=" + kda +
                '}' + "object.toString="+ super.toString();
    }
}
