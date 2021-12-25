package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection() {
        Hello target = new Hello();
        //공통 로직 1 시작;
        log.info("start");
        String result1 = target.callA();    // 호출하는 메서드만 다름
        log.info("result1={}", result1);
        //공통 로직 1 종료

        //공통 로직 2 시작;
        log.info("start");
        String result2 = target.callB();    // 호출하는 메서드만 다름
        log.info("result2={}", result2);
        //공통 로직 2 종료
    }

    @Test
    void reflection1() throws Exception {
        log.info("result1={}", result("callA"));
        log.info("result2={}", result("callB"));
    }

    private Object result(String methodName) throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello hello = new Hello();
        Method method = classHello.getMethod(methodName);
        return method.invoke(hello);
    }

    @Test
    void reflection2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello hello = new Hello();

        Method method = classHello.getMethod("callA");
        dynamicCall(hello, method);

        method = classHello.getMethod("callB");
        dynamicCall(hello, method);
    }

    private void dynamicCall(Object target, Method method) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);    // 호출하는 메서드만 다름
        log.info("result={}", result);
    }

    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
