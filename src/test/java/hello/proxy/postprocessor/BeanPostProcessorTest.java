package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        ClassB beanB = context.getBean("beanA", ClassB.class);
        beanB.helloB();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(BasicTest.ClassA.class));

    }

    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public ClassA classA() {
            return new ClassA();
        }

        @Bean
        public AtoB aToB() {
            return new AtoB();
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

    static class AtoB implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={}, bean={}", beanName, bean.getClass());
            if (bean instanceof ClassA) {
                return new ClassB();
            }
            return bean;
        }
    }


}
