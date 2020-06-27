package cn.cxd.models;

public class User {
    private int id;
    private String username;
    private String password;
    private int age;
    private int kda;

    public User() {};

    public User(int id, String username, String password, int age, int kda) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.kda = kda;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", kda=" + kda +
                '}';
    }
}
