package hello.proxy.config.v2_dynamic_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamic_proxy.handlerr.LogTraceBasicHandler;
import hello.proxy.config.v2_dynamic_proxy.handlerr.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicFilterProxyConfig {

    private final static String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1 target = new OrderControllerV1Impl(orderService(logTrace));
        OrderControllerV1 proxyInstance = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class}, new LogTraceFilterHandler(logTrace, target, PATTERNS));
        return proxyInstance;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepository(logTrace));
        OrderServiceV1 proxyInstance = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class}, new LogTraceFilterHandler(logTrace, target, PATTERNS));
        return proxyInstance;
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxyInstance = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class}, new LogTraceFilterHandler(logTrace, target, PATTERNS));
        return proxyInstance;
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
