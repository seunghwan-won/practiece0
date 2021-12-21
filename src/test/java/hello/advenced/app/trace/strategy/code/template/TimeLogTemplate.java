package hello.advenced.app.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {
    public void execute(Callback callback) {
        long startTimeMs = System.currentTimeMillis();
        // 비즈니스 로직 시작
        callback.call();    // 위임
        // 비즈니스 로직 종료
        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTimeMs={}", resultTimeMs);
    }
}
