package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

public class Pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){} // 포인트컷 시그니쳐

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {

    }

    @Pointcut("execution(* hello.aop.order..*(..)) && execution(* *..*Service.*(..))")
    public void allOrderAndService(){} // 포인트컷 시그니쳐

}
