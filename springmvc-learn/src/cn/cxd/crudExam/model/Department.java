package cn.cxd.crudExam.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:34 2020/6/15
 * @Modified By:
 */
public class Department {
    private Integer deptId;
    private String deptName;

    public Department() {
    }



    @Override
    public String toString() {
        return "Department{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public Department(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
