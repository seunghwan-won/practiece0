package hello.advenced.app.trace.strategy.code.strategy;

import hello.advenced.app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTimeMs = System.currentTimeMillis();
        // 비즈니스 로직 시작
        strategy.call();    // 위임
        // 비즈니스 로직 종료
        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTimeMs={}", resultTimeMs);
    }
}
