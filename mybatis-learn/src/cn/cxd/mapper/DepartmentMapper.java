package cn.cxd.mapper;

import cn.cxd.model.Department;
import cn.cxd.vo.DepartmentWithEmployee;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:08 2020/5/29
 * @Modified By:
 */
public interface DepartmentMapper {
    public Department selectSimple(int id);
    public Department selectStep(int id);
    public DepartmentWithEmployee selectDepartmentWithEmployee(int id);
    public DepartmentWithEmployee selectDepartmentWithEmployeeStep(int id);
}
