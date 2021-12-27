package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class JdkDynamicProxyA {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, handler);
        proxy.call();
        // assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue(); // proxyFactory를 통해서 생성된 프록시만 확인가능
        log.info("target class={}",target.getClass());
        log.info("proxy class={}",proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
                new Class[]{BInterface.class}, handler);
        proxy.call();
        log.info("target class={}",target.getClass());
        log.info("proxy class={}",proxy.getClass());
    }
}
