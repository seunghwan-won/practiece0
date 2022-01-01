package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WithinTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void withinExact() throws NoSuchMethodException {
        pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
        method = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
        // 표현식에 해당되는 메소드는 모두 통과
    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(hello.aop.member.annotation.*Service*)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(hello.aop..*)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinFalse() {
        // 정확한 타입 지정해야함
        pointcut.setExpression("within(hello.aop.member.MemberService)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

}
