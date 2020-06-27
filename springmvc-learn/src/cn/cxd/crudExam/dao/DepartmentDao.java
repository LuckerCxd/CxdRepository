package cn.cxd.crudExam.dao;

import cn.cxd.crudExam.model.Department;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:55 2020/6/15
 * @Modified By:
 */

@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = new HashMap();
    static {
        departments.put(1,new Department(1,"A部门"));
        departments.put(2,new Department(2,"B部门"));
        departments.put(3,new Department(3,"C部门"));
        departments.put(4,new Department(4,"D部门"));
        departments.put(5,new Department(5,"E部门"));
    }

    public static Department get(Integer id){
        return departments.get(id);
    }
    public static Collection<Department> getAll(){
        return departments.values();
    }
}
