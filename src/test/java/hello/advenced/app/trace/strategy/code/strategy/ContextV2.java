package hello.advenced.app.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV2 {
    public void execute(Strategy strategy) {
        long startTimeMs = System.currentTimeMillis();
        // 비즈니스 로직 시작
        strategy.call();    // 위임
        // 비즈니스 로직 종료
        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTimeMs={}", resultTimeMs);
    }
}
