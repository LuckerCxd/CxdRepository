package cn.cxd.mapper;

import cn.cxd.model.Employee;
import cn.cxd.vo.EmployeeWithDept;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 15:41 2020/5/29
 * @Modified By:
 */
public interface EmployeeMapper {
    public boolean insert(Employee employee);
    public boolean insertUseKey(Employee employee);
    public Employee selectById(int id);
    public List<Employee> selectList(int id);
    public Collection<Employee> selectCollection(int id);
    public Employee[] selectArray(int id);
    public Map<String,Object> selectMap(int id);

    @MapKey("id")
    public Map<Integer,Employee> selectEmployeeMap(int id);

    @MapKey("id")
    public Map<Integer,Employee> selectMultiParm(@Param("id") int id, @Param("gender") int gender);

    @MapKey("id")
    public Map<Integer,Employee> selectOrderBy(@Param("id") int id, @Param("column") String column);

    @MapKey("id")
    public Map<Integer,Employee> selectSimpleMap(int id);

    public EmployeeWithDept selectEmployeeWithDept(int id);

    public EmployeeWithDept selectEmployeeStep(int id);

    public Employee selectEmployeeDiscriminator(int id);

    @MapKey("id")
    public Map<Integer,Employee> selectByWhereStat(Map map);

    @MapKey("id")
    public Map<Integer,Employee> selectByWhereStat2(Map map);

    @MapKey("id")
    public Map<Integer,Employee> selectByTrimStat(Map map);

    public Employee selectByChooseStat(int gender);

    public boolean updateBySetStat(Map map);
    public boolean updateByTrimStat(Map map);

    @MapKey("id")
    public Map<Integer,Employee> selectForeachStat(List ids);

    public boolean insertForeachStat1(List<Employee> employees);
    public boolean insertForeachStat2(Set<Employee> employees);

    @MapKey("id")
    public Map<Integer,Employee> selectForeachStat2(Map map);

    @MapKey("id")
    public Map<Integer,Employee> selectForeachStat3(@Param("idList") List idList,@Param("gender") int gender);

    @MapKey("id")
    public Map<Integer,Employee> selectLike(String name);

    @MapKey("id")
    public Map<Integer,Employee> selectLike2(String name);

    @MapKey("id")
    public Map<Integer,Employee> selectLike3(String name);

    public Employee selectSqlStat(int id);


}
