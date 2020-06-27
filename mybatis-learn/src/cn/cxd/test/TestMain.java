package cn.cxd.test;

import cn.cxd.mapper.DepartmentMapper;
import cn.cxd.model.Department;
import cn.cxd.model.Employee;
import cn.cxd.mapper.EmployeeMapper;
import cn.cxd.vo.DepartmentWithEmployee;
import cn.cxd.vo.EmployeeWithDept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 12:13 2020/5/29
 * @Modified By:
 */
public class TestMain {
    public static void main(String[] args) throws IOException {

    }

    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            sqlSession.insert("insertSimple");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.selectSimple(1);
            System.out.println(department);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            sqlSession.getMapper(EmployeeMapper.class).insert(
                    new Employee(1,"Leo","lio","leo@qq.com",1,1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            boolean b = sqlSession.getMapper(EmployeeMapper.class).insert(
                    new Employee(2,"Mary", "mary", "Mary@qq.com", 2, 1));
            System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test5() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Employee employee = sqlSession.getMapper(EmployeeMapper.class).selectById(1);
            System.out.println(employee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test6() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            boolean b = sqlSession.getMapper(EmployeeMapper.class).insertUseKey(
                    new Employee("Frank", 1, "Frank@qq.com", "frank", 1));
            System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test7() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            List<Employee> collection = sqlSession.getMapper(EmployeeMapper.class).selectList(1);
            for(Employee employee : collection)
                System.out.print(employee+" ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test8() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Employee[] collection = sqlSession.getMapper(EmployeeMapper.class).selectArray(1);
            for(Employee employee : collection)
                System.out.print(employee+" ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test9() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Collection<Employee> collection = sqlSession.getMapper(EmployeeMapper.class).selectCollection(1);
            for(Employee employee : collection)
                System.out.print(employee+" ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test 
    public void test10() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<String,Object> map = sqlSession.getMapper(EmployeeMapper.class).selectMap(1);
            System.out.println(map);
            /*Set<String> strings = map.keySet();
            for(String s : strings){
                System.out.print(map.get(s)+" ");
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test11() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> employeeMap = sqlSession.getMapper(EmployeeMapper.class).selectEmployeeMap(1);
            System.out.println(employeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test12() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> employeeMap = sqlSession.getMapper(EmployeeMapper.class).selectMultiParm(1,1);
            System.out.println(employeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test13() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> employeeMap = sqlSession.getMapper(EmployeeMapper.class).selectOrderBy(1,"id");
            System.out.println(employeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test14() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> employeeMap = sqlSession.getMapper(EmployeeMapper.class).selectSimpleMap(1);
            System.out.println(employeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test15() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            EmployeeWithDept employeeWithDept = sqlSession.getMapper(EmployeeMapper.class).selectEmployeeWithDept(1);
            System.out.println(employeeWithDept);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test16() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            EmployeeWithDept employeeWithDept = sqlSession.getMapper(EmployeeMapper.class)
                    .selectEmployeeStep(1);
            System.out.println(employeeWithDept);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test17() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            DepartmentWithEmployee departmentWithEmployee = sqlSession.getMapper(DepartmentMapper.class)
                    .selectDepartmentWithEmployee(1);
            System.out.println(departmentWithEmployee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test18() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            DepartmentWithEmployee departmentWithEmployee = sqlSession.getMapper(DepartmentMapper.class)
                    .selectDepartmentWithEmployeeStep(1);
            System.out.println(departmentWithEmployee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test19() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Employee employee = sqlSession.getMapper(EmployeeMapper.class)
                        .selectEmployeeDiscriminator(1);
            System.out.println(employee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test20() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("deptId",1);
            map.put("gender",1);
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectByWhereStat(map);
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test21() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("deptId",1);
            map.put("gender",1);
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectByWhereStat2(null);
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test22() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("deptId",1);
            map.put("gender",1);
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectByTrimStat(map);
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test23() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Employee employee = sqlSession.getMapper(EmployeeMapper.class)
                    .selectByChooseStat(1);
            System.out.println(employee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test24() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("deptId",2);
            map.put("gender",2);
            map.put("id",1);
            boolean b = sqlSession.getMapper(EmployeeMapper.class)
                    .updateBySetStat(map);
            System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test25() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("deptId",2);
            map.put("gender",2);
            map.put("id",1);
            boolean b = sqlSession.getMapper(EmployeeMapper.class)
                    .updateByTrimStat(map);
            System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test26() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(2);
            ids.add(3);
            Map<Integer, Employee> map = sqlSession.getMapper(EmployeeMapper.class)
                    .selectForeachStat(ids);
            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test27() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(new Employee("Mia",2, "Mia@qq.com", "mia", 1));
            employees.add(new Employee("Cacy",1, "Cacy@qq.com", "cacy", 1));
            employees.add(new Employee("Luby",1, "Luby@qq.com", "luby", 1));
            boolean b = sqlSession.getMapper(EmployeeMapper.class)
                    .insertForeachStat1(employees);
            System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test28() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Set<Employee> employees = new HashSet();
            employees.add(new Employee("Mia",2, "Mia@qq.com", "mia", 1));
            employees.add(new Employee("Cacy",1, "Cacy@qselectByWhereStat2q.com", "cacy", 1));
            employees.add(new Employee("Luby",1, "Luby@qq.com", "luby", 1));
            boolean b = sqlSession.getMapper(EmployeeMapper.class)
                    .insertForeachStat2(employees);
            System.out.println(b);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test29() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("gender",1);
            map.put("idList",Arrays.asList(1,2,3));
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectForeachStat2(map);
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test30() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectForeachStat3(Arrays.asList(1,2,3),1);
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test31() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectLike("%a%");
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test32() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectLike2("a");
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test33() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Map<Integer, Employee> integerEmployeeMap = sqlSession.getMapper(EmployeeMapper.class)
                    .selectLike3("a");
            System.out.println(integerEmployeeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test34() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            Employee employee= sqlSession.getMapper(EmployeeMapper.class)
                    .selectSqlStat(1);
            sqlSession.clearCache();
            Employee employee2= sqlSession.getMapper(EmployeeMapper.class)
                    .selectSqlStat(1);
            System.out.println(employee==employee2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test35() throws IOException {
        Employee employee = null;
        Employee employee2 = null;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            employee= sqlSession.getMapper(EmployeeMapper.class)
                    .selectSqlStat(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            employee2 = sqlSession.getMapper(EmployeeMapper.class)
                    .selectSqlStat(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(employee == employee2);
    }
}
