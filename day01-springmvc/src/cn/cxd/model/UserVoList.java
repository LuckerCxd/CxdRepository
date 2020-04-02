package cn.cxd.model;

import java.util.List;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 21:47 2020/3/30
 * @Modified By:
 */
public class UserVoList {
    private List<String> hobbies;

    @Override
    public String toString() {
        return "UserVoList{" +
                "hobbies=" + hobbies +
                '}';
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
