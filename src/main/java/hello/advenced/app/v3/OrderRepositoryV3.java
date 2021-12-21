package hello.advenced.app.v3;

import hello.advenced.app.trace.LogTrace;
import hello.advenced.app.trace.TraceId;
import hello.advenced.app.trace.TraceStatus;
import hello.advenced.app.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.save()");

            if(itemId.equals(("ex"))) {
                throw new IllegalArgumentException("예외 발생");
            }

            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 다시 던저야한다.
        }
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
