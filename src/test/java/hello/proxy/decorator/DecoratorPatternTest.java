package hello.proxy.decorator;

import hello.proxy.decorator.code.*;
import hello.proxy.pure_proxy.code.CacheProxy;
import hello.proxy.pure_proxy.code.ProxyPatternClient;
import hello.proxy.pure_proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {
    @Test
    void noDecorateTest() {
        RealComponent component = new RealComponent();
        DecoratorPatternClient decorator = new DecoratorPatternClient(component);
        decorator.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
