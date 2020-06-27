package cn.cxd.vo;

import cn.cxd.model.Employee;

import java.util.List;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:23 2020/5/30
 * @Modified By:
 */
public class DepartmentWithEmployee {
    private int deptId;
    private String deptName;
    private List<Employee> employees;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "DepartmentWithEmployee{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", employees=" + employees +
                '}';
    }

    public DepartmentWithEmployee() {
    }
}
