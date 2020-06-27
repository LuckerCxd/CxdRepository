package cn.cxd.vo;

import cn.cxd.model.Department;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 17:29 2020/5/30
 * @Modified By:
 */
public class EmployeeWithDept {
    private int id;
    private String name;
    private int gender;
    private String email;
    private String lastName;
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeWithDept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                '}';
    }

    public EmployeeWithDept() {
    }
}
