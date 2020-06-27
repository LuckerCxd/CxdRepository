package cn.cxd.beans;

import cn.cxd.impls.IStudentService;

public class StudentSeviceImpl implements IStudentService {
    private int age;

    @Override
    public int addStudentUser() {
        System.out.println("StudentSeviceImpl addStudentSevice...");
        return 0;
    }

    @Override
    public void delStudentUser() {
        System.out.println("StudentSeviceImpl delStudent..");
    }

    @Override
    public boolean updateStudentUser(int newAge) {
        System.out.println("StudentSeviceImpl updateStudent...");
        this.age = newAge;
        return true;
    }


    private boolean checkStudentUser(int newAge) {
        System.out.println("StudentSeviceImpl checkStudentUser...");
        this.age = newAge;
        return true;
    }

}
