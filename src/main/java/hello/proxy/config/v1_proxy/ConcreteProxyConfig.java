package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderController_c(LogTrace logTrace) {
        OrderControllerV2 target = new OrderControllerV2(orderService_c(logTrace));
        return new OrderControllerConcreteProxy(target, logTrace);
    }

    @Bean
    public OrderServiceV2 orderService_c(LogTrace logTrace) {
        OrderServiceV2 target = new OrderServiceV2(orderRepository_c(logTrace));
        return new OrderServiceConcreteProxy(target, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository_c(LogTrace logTrace) {
        OrderRepositoryV2 target = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(target, logTrace);
    }

    @Bean
    public LogTrace logTrace_c() {
        return new ThreadLocalLogTrace();
    }
}
