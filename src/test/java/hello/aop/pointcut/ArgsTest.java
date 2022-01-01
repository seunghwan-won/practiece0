package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ArgsTest {
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args() {
        assertThat(pointcut("args(String)").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("args(Object)").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("args()").matches(method, MemberServiceImpl.class))
                .isFalse();
        assertThat(pointcut("args(..)").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("args(*)").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("args(String, ..)").matches(method, MemberServiceImpl.class))
                .isTrue();
    }

    /**
     * execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
     * args(java.io.Serializable): 런타임에 전달괸 변수로 판단 (동적)
     */

    @Test
    void argsVsExecution() {
        assertThat(pointcut("args(String)").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("args(Object)").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("args(java.io.Serializable)").matches(method, MemberServiceImpl.class))
                .isTrue();
        // 상위타입 메소드 파라미터 허용

        assertThat(pointcut("execution(* *.*(String))").matches(method, MemberServiceImpl.class))
                .isTrue();
        assertThat(pointcut("execution(* *.*(Object))").matches(method, MemberServiceImpl.class))
                .isFalse();
        assertThat(pointcut("execution(* *.*(java.io.Serializable))").matches(method, MemberServiceImpl.class))
                .isFalse();
        // 상위타입 메소드 파라미터 허용 X
    }
}
