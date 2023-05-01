package api.apispring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  // @Aspect : 이 어노테이션을 작성해야 AOP로 사용할 수 있다.
@Component
public class TimeTraceAop {
    @Around("execution(* api.apispring..*(..))")  // service만 찍고싶으면 api.apispring.service..*(..)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+" ms");
        }
    }
}
