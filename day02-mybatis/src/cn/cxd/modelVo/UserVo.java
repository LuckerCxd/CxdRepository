package cn.cxd.modelVo;

import cn.cxd.model.Order;
import cn.cxd.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:48 2020/3/9
 * @Modified By:
 */
public class UserVo {
    private int id;
    private User user;
    private Integer[] idsArray;
    private List<Integer> idsList;
    private List<Order> ordersList;


    public UserVo() {}


    public UserVo(User user, Integer[] idsArray, List<Integer> idsList) {
        this.user = user;
        this.idsArray = idsArray;
        this.idsList = idsList;
    }

    public UserVo(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", user=" + user +
                ", idsArray=" + Arrays.toString(idsArray) +
                ", idsList=" + idsList +
                ", ordersList=" + ordersList +
                '}';
    }
}
