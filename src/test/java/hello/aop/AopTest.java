package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV4Log;
import hello.aop.order.aop.AspectV4Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
//@Import({AspectV1.class})
//@Import({AspectV2.class})
//@Import({AspectV3.class})
@Import({AspectV4Log.class, AspectV4Transaction.class})
@SpringBootTest
class AopTest {

    @Autowired
    OrderService service;
    @Autowired
    OrderRepository repository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}, orderRepository={}", AopUtils.isAopProxy(service),
                AopUtils.isAopProxy(repository));
    }

    @Test
    void success() {
        service.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() ->
                service.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}