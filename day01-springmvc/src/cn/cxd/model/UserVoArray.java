package cn.cxd.model;

import java.util.Arrays;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 17:57 2020/3/30
 * @Modified By:
 */
public class UserVoArray {

    private String[] hobbies;

    @Override
    public String toString() {
        return "uservoArray{" +
                "hobbies=" + Arrays.toString(hobbies) +
                '}';
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }
}
