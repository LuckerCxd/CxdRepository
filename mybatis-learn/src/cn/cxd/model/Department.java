package cn.cxd.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 12:11 2020/5/29
 * @Modified By:
 */
public class Department {
    private int deptId;
    private String deptName;


    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public Department() {
    }

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
}
