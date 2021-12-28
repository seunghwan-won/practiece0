package hello.proxy.proxy_factory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceIml;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @Test
    void interfaceProxy() {
        ServiceInterface target = new ServiceIml();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save();
        proxy.find();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxy_factory를 통해서 만들었을떄만 확인가능 , 직접 만든건 x
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse(); // proxy_factory를 통해서 만들었을떄만 확인가능 , 직접 만든건 x
    }

    @Test
    void classProxy() {
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxy_factory를 통해서 만들었을떄만 확인가능 , 직접 만든건 x
    }

    @Test
    void proxyTargetClass () {
        ServiceInterface target = new ServiceIml();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);         // true : cglib 무적권 사용
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save();
        proxy.find();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxy_factory를 통해서 만들었을떄만 확인가능 , 직접 만든건 x
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); // proxy_factory를 통해서 만들었을떄만 확인가능 , 직접 만든건 x
    }
}
