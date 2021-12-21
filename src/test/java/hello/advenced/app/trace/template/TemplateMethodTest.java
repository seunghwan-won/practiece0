package hello.advenced.app.trace.template;

import hello.advenced.app.trace.template.code.AbstractTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate logic1 = new SubClassLogic1();
        logic1.execute();
        AbstractTemplate logic2 = new SubClassLogic1();
        logic2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        template1.execute();
        log.info("클래스 이름1={}", template1.getClass());
        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        template2.execute();
        log.info("클래스 이름2={}", template2.getClass());
    }

    private void logic1() {
        long startTimeMs = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime={}", resultTimeMs);
    }

    private void logic2() {
        long startTimeMs = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime={}", resultTimeMs);
    }
}
