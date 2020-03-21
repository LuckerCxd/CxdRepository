package cn.cxd.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:12 2020/3/20
 * @Modified By:
 */
public class User {
    private int id;
    private String username;
    private String password;

    public User() { }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
