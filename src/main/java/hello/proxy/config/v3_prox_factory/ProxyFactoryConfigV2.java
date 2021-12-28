package hello.proxy.config.v3_prox_factory;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_prox_factory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 controller() {
        OrderControllerV2 target = new OrderControllerV2(service());
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace()));
        OrderControllerV2 proxy = (OrderControllerV2) proxyFactory.getProxy();
        log.info("proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 service() {
        OrderServiceV2 target = new OrderServiceV2(repository());
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace()));
        OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();
        log.info("proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 repository() {
        OrderRepositoryV2 target = new OrderRepositoryV2();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace()));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();
        log.info("proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        Advice advice = new LogTraceAdvice(logTrace);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;
    }

}
