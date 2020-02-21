package cn.cxd.annoaop.aspects;

public class StuSeviceAspect {
    public static void before(){
        System.out.println("方法前使用StuSeviceAspect的before方法");
    }
    public static void after(){
        System.out.println("方法后使用StuSeviceAspect的after方法");
    }
}
