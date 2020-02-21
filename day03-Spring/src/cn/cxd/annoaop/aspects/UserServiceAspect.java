package cn.cxd.annoaop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserServiceAspect {

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around 放行前");
        Object reObj = joinPoint.proceed();
        System.out.println("around 放行后");
        return reObj;
    }

    @Pointcut("execution(* cn.cxd.*.*.add*(..))")
    public void pointCut(){};

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before 方法: "+joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCut()",returning = "reObj")
    public void afterReturning(JoinPoint joinPoint,Object reObj){
        System.out.println("afterReturning 返回值： "+reObj);
    }

    @After(value = "pointCut()")
    public void after(){
        System.out.println("after 最终方法");
    }

    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        System.out.println("afterThrowing 方法："+e.getCause());
    }

}
