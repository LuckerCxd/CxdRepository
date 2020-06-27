package cn.cxd.annotationAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 10:28 2020/5/10
 * @Modified By:
 */

@Aspect
@Component
public class ServiceAop {

    @Pointcut("execution( public * cn.cxd.service.*.*(..))")
    public void myPointCut(){};

/*

    @Before("myPointCut()")
    public void logBefore(JoinPoint joinPoint){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date())+" "+joinPoint.getSignature().getName()+" args :"+joinPoint.getArgs()[0]+
                " , "+joinPoint.getArgs()[1]);
    }

    @After(value = "myPointCut()" )
    public void logFinally(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+" args :"+joinPoint.getArgs()[0]+
                " , "+joinPoint.getArgs()[1]+ "  finally");
    }

    @AfterReturning(pointcut = "myPointCut()",returning = "reObj")
    public void logAfterReturing(JoinPoint joinPoint,String reObj){
        System.out.println(joinPoint.getSignature().getName()+" args :"+joinPoint.getArgs()[0]+
                " , "+joinPoint.getArgs()[1]+" AfterReturning reObj: "+reObj);
    }

    @AfterThrowing(value = "myPointCut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,NullPointerException e){
        System.out.println("throw Exceptrion : "+e.getMessage());
    }
*/

    @Around("myPointCut()")
    public Object logAround(ProceedingJoinPoint pjp){
        Object reObj = null;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(simpleDateFormat.format(new Date())+" "+pjp.getSignature().getName()+
                    " args :"+pjp.getArgs()[0]+
                    " , "+pjp.getArgs()[1]);
            reObj = pjp.proceed();
            System.out.println(pjp.getSignature().getName()+" args :"+pjp.getArgs()[0]+
                    " , "+pjp.getArgs()[1]+" AfterReturning reObj: "+reObj);
        }catch (Throwable e){
            System.out.println("throw Throwable : "+e.getMessage());
        }finally {
            System.out.println(pjp.getSignature().getName()+" args :"+pjp.getArgs()[0]+
                    " , "+pjp.getArgs()[1]+ "  finally");
        }
        return reObj;
    }

}
