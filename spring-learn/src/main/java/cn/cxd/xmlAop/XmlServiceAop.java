package cn.cxd.xmlAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 22:58 2020/5/12
 * @Modified By:
 */
public class XmlServiceAop {
    public void before(JoinPoint joinPoint){
        System.out.println("Before : Method Name:" + joinPoint.getSignature().getName()
        +" args: "+joinPoint.getArgs()[0]+" , "+joinPoint.getArgs()[1]);
    }

    public void after(JoinPoint joinPoint){
        System.out.println("AfterFinally");
    }


    public Object afterReturning(JoinPoint joinPoint,Object reObj){
        System.out.println("Returning : "+ reObj);
        return reObj;
    }

    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        System.out.println("Throwing : "+ ex.getMessage());
    }

    public Object around(ProceedingJoinPoint joinPoint){
        Object reObj = null;
        try {
            System.out.println("Before : Method Name:" + joinPoint.getSignature().getName()
                    +" args: "+joinPoint.getArgs()[0]+" , "+joinPoint.getArgs()[1]);
            reObj = joinPoint.proceed();
            System.out.println("Returning : "+ reObj);
        }catch (Throwable throwable){
            System.out.println("Throwing : "+ throwable.getMessage());
        }finally {
            System.out.println("AfterFinally");
        }
        return reObj;
    }
}
