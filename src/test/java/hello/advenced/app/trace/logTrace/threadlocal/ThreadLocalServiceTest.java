package hello.advenced.app.trace.logTrace.threadlocal;

import hello.advenced.app.trace.logTrace.threadlocal.code.FieldService;
import hello.advenced.app.trace.logTrace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService fieldService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("A");
        Thread threadB = new Thread(userB);
        threadB.setName("B");

        threadA.start();
        sleep(200);    // 동시성 문제 발생
        threadB.start();

        sleep(3000);    // 메인 쓰레드 종료 대기

        log.info("main end");
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
