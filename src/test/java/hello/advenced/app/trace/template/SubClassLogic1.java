package hello.advenced.app.trace.template;

import hello.advenced.app.trace.template.code.AbstractTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic1 extends AbstractTemplate {
    @Override
    protected void call() {
        log.info("비즈니스 로직 1 실행");
    }
}
