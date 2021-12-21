package hello.advenced.app.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTimeMs = System.currentTimeMillis();
        // 비즈니스 로직 실행
        call(); // 상속
        // 비즈니스 로직 종료
        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime={}", resultTimeMs);
    }

    protected abstract void call();
}
