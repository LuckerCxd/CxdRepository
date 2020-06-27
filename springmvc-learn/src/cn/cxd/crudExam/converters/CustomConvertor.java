package cn.cxd.crudExam.converters;


import cn.cxd.crudExam.dao.DepartmentDao;
import cn.cxd.crudExam.dao.EmployeeDao;
import cn.cxd.crudExam.model.Department;
import cn.cxd.crudExam.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 10:04 2020/6/16
 * @Modified By:
 */

public class CustomConvertor implements Converter<String, Employee> {
    @Override
    public Employee convert(String s) {
        String[] split = s.split("-");
        if(split != null && split.length == 4){
            Employee employee = new Employee();
            employee.setLastName(split[0]);
            employee.setEmail(split[1]);
            int gender = split[2].equals("女")?0:1;
            employee.setGender(gender);
            employee.setDepartment(DepartmentDao.get(Integer.parseInt(split[3])));
            return employee;
        }
        return null;
    }
}
