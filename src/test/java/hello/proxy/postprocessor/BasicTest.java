package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BasicTest {

    @Test
    void basicConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);
        ClassA beanA = context.getBean("beanA", ClassA.class);
        beanA.helloA();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(ClassB.class));
    }

    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public ClassA classA() {
            return new ClassA();
        }

        @Bean(name = "beanB")
        public ClassA classB() {
            return new ClassA();
        }
    }

    static class ClassA {
         public void helloA() {
             log.info("helloA");
         }
    }

    static class ClassB {
        public void helloB() {
            log.info("helloB");
        }
    }

}
