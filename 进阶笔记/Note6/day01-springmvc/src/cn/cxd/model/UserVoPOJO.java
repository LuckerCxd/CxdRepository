package cn.cxd.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 20:33 2020/3/30
 * @Modified By:
 */
public class UserVoPOJO {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserVoPOJO{" +
                "user=" + user +
                '}';
    }
}
