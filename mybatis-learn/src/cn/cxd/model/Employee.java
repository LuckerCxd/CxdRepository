package cn.cxd.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 12:11 2020/5/29
 * @Modified By:
 */
public class Employee {
    private int id;
    private String name;
    private int gender;
    private String email;
    private String lastName;
    private int deptId;

    public Employee(int id, String name, int gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Employee(String name, int gender, String email, String lastName, int deptId) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.lastName = lastName;
        this.deptId = deptId;
    }

    public Employee() {
    }

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

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public Employee(int id, String name, String lastName, String email, int gender, int deptId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.lastName = lastName;
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
