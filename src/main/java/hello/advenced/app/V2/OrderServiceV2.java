package hello.advenced.app.V2;

import hello.advenced.app.trace.TraceId;
import hello.advenced.app.trace.TraceStatus;
import hello.advenced.app.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 trace;

    public void orderItem(String itemId, TraceId traceId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderController.orderItem()");
            orderRepositoryV2.save(itemId, traceId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 다시 던저야한다.
        }

    }
}
