package hello.advenced.app.trace.strategy;

import hello.advenced.app.trace.strategy.code.strategy.ContextV1;
import hello.advenced.app.trace.strategy.code.strategy.StrategyLogic1;
import hello.advenced.app.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        ContextV1 v1 = new ContextV1(new StrategyLogic1());
        v1.execute();

        ContextV1 v2 = new ContextV1(new StrategyLogic2());
        v2.execute();
    }
    @Test
    void strategyV2() {
        ContextV1 v1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        v1.execute();

        ContextV1 v2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
        v2.execute();
    }
}
