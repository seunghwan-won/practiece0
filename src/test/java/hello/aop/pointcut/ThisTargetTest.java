package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * application.properties
 * spring.aop.proxy-target-class=true CGLIB (기본값)
 * spring.aop.proxy-target-class=false jdk동적프록시 CGLIB 사
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ThisTargetTest {
    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    static class ThisTargetAspect {

        @Around("this(hello.aop.member.MemberService)")
        public Object doThisInterface1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[doThisInterface1]={}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(hello.aop.member.MemberService)")
        public Object doTargetInterface1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[doTargetInterface1]={}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object doThisInterface2(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[doThisInterface2]={}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object doTargetInterface2(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[doTargetInterface2]={}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
