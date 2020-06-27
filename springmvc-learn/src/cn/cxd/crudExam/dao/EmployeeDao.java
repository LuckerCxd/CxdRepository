package cn.cxd.crudExam.dao;

import cn.cxd.crudExam.model.Department;
import cn.cxd.crudExam.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:29 2020/6/15
 * @Modified By:
 */

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = new HashMap();

    private static Integer initId = 6;

    static {
        employees.put(1, new Employee(1, "Mia", "mia@qq.com",
                0, new Department(1, "A部门")));
        employees.put(2, new Employee(2, "Lake", "lake@qq.com",
                1, new Department(2, "B部门")));
        employees.put(3, new Employee(3, "Frank", "frank@qq.com",
                1, new Department(3, "C部门")));
        employees.put(4, new Employee(4, "Boe", "boe@qq.com",
                0, new Department(4, "D部门")));
        employees.put(5, new Employee(5, "Gia", "gia@qq.com",
                0, new Department(5, "E部门")));
    }

    public void remove(Integer id) {
        employees.remove(id);
    }

    public Employee get(Integer id){
        return employees.get(id);
    }

    public Collection<Employee> getAll(){
       return employees.values();
    }

    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId);
            initId++;
        }
        employee.setDepartment(DepartmentDao.get(employee.getDepartment().getDeptId()));
        employees.put(employee.getId(),employee);
    }

}
