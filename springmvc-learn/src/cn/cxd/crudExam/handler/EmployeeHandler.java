package cn.cxd.crudExam.handler;

import cn.cxd.crudExam.dao.DepartmentDao;
import cn.cxd.crudExam.dao.EmployeeDao;
import cn.cxd.crudExam.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 12:06 2020/6/15
 * @Modified By:
 */

@Controller
@RequestMapping("/curd")
public class EmployeeHandler {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public String emps(ModelMap map) {
//        map.addAttribute("departments",departmentDao.getAll());
        map.addAttribute("employees",employeeDao.getAll());
        return "curd/list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String emp(ModelMap map) {
        Map<String,String> genders = new HashMap();
        genders.put("0","Female");
        genders.put("1","Male");
        map.addAttribute("genders",genders);
        map.addAttribute("employee",new Employee());
        map.addAttribute("departments",departmentDao.getAll());
        return "curd/input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String empPut(@Valid Employee employee, BindingResult bindingResult, ModelMap map) {
        if(bindingResult.getErrorCount() > 0){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError error:fieldErrors){
                System.out.println(error.getField()+"   "+error.getDefaultMessage());
            }
            Map<String,String> genders = new HashMap();
            genders.put("0","Female");
            genders.put("1","Male");
            map.addAttribute("genders",genders);
            map.addAttribute("departments",departmentDao.getAll());
            return "curd/input";
        }
        employeeDao.save(employee);
        return "redirect:/curd/emps";
    }


    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String empDel(@PathVariable("id") Integer id) {
        employeeDao.remove(id);
        return "redirect:/curd/emps";
    }



    @ModelAttribute(value = "employee")
    public void serviceForUpdate(@RequestParam(value = "id",required = false) Integer id,
                                 ModelMap map) {
        if(id != null){
            map.addAttribute("employee",employeeDao.get(id));
            System.out.println("ma: "+employeeDao.get(id));
        }
    }


    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String empEdit(@PathVariable("id") Integer id,ModelMap map) {
        Map<String,String> genders = new HashMap();
        genders.put("0","Female");
        genders.put("1","Male");
        map.addAttribute("genders",genders);
        map.put("employee", employeeDao.get(id));
        map.addAttribute("departments",departmentDao.getAll());
        return "curd/input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String empUpdate(@ModelAttribute("employee") @Valid Employee employee,
                            BindingResult bindingResult,ModelMap map) {
        if(bindingResult.getErrorCount() > 0){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError error:fieldErrors){
                System.out.println(error.getDefaultMessage());
            }
            map.addAttribute("employee",employee);
            return "redirect:/curd/emp/"+employee.getId();
        }
        employeeDao.save(employee);
        System.out.println(employee);
        return "redirect:/curd/emps";
    }

    @RequestMapping("/empConvertor")
    public String empConvertor(@RequestParam(value = "source",required = false) Employee employee) {
        if(employee != null){
            employeeDao.save(employee);
        }
        return "redirect:/curd/emps";
    }

    @InitBinder
    public void disableGender(WebDataBinder webDataBinder){
        /*List<Validator> validators = webDataBinder.getValidators();
        System.out.print("validator: ");
        for(Validator v: validators){
            System.out.print(v.getClass()+" ");
        }
        System.out.println();*/
        webDataBinder.setDisallowedFields("lastName");
    }

    @ResponseBody
    @RequestMapping("/testResponseBody")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println("body:"+body);
        return "helloworld! " + new Date();
    }


    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testHttpMessageConverter(HttpSession sesssion) throws IOException {
        InputStream in = sesssion.getServletContext().getResourceAsStream("scripts/jquery-1.9.1.min.js");
        byte[] bytes = new byte[in.available()];
        in.read(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=jquery-1.9.1.min.js");
        HttpStatus status = HttpStatus.OK;

        ResponseEntity<byte[]> responseEntity = new ResponseEntity(bytes,headers,status);
        System.out.println(responseEntity);
        return responseEntity;
    }
}
