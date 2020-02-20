package cn.cxd.springAopXmlConfig.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

public class MyAspect {
    public void before(JoinPoint joinPoint){
        System.out.println("方法前使用StuSeviceAspect的before方法 : "+ " args: "+joinPoint.getArgs()+" kind: "+joinPoint.getKind()+" target: "+joinPoint.getTarget()+" signature: "+joinPoint.getSignature()+" staticPart: "+joinPoint.getStaticPart()+" this: "+joinPoint.getThis()+" sourceLocation: "+joinPoint.getSourceLocation());

    }
    public void after(){
        System.out.println("方法最终StuSeviceAspect的after方法 : ");
    }
    public void afterReturning(JoinPoint joinPoint,Object reObj) {
        System.out.println("方法后使用StuSeviceAspect的afterReturning方法 返回值为： "+reObj);
    }
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("方法环绕前使用StuSeviceAspect的around方法 ");
        Object reObj = proceedingJoinPoint.proceed();
        System.out.println("方法环绕后使用StuSeviceAspect的around方法 ");
        return reObj;
    }
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        System.out.println("方法异常后出现afterThrowing joinPoint: "+joinPoint.getSignature().getName()+" ex: "+e);
    }
}
