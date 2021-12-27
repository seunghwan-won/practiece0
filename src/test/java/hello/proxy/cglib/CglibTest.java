package hello.proxy.cglib;

import hello.proxy.cglib.cod.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.cglib.proxy.Enhancer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class CglibTest {
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("target class={}", target.getClass().getSimpleName());
        log.info("proxy class={}", proxy.getClass().getSimpleName());

        proxy.call();

        //assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); //proxyFactory를 통해서 생성된 프록시만 확인가능
    }
}
