package cn.cxd.modelMapper;

import cn.cxd.model.User;
import cn.cxd.modelVo.UserVo;

import java.util.List;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:26 2020/3/9
 * @Modified By:
 */
public interface UserMapper {
    public User findUserByid(int id);
    public List<User> findUserByName(String name);
    public User findUserByidUseResultMap(int id);
    public int insert(User user);

    public List<UserVo> findUserByArrayUnionList(UserVo userVo);
    public List<User> findUserByArrayInList(UserVo userVo);
    public List<UserVo> findUserByArrayInListRecordIdsToList(UserVo userVo);
    public List<UserVo> findUserByIdRecordOrders(UserVo userVo);
    public List<Integer> findUserIntegers(UserVo userVo);
}
