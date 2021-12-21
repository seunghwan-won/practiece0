package hello.advenced.app.v4;

import hello.advenced.app.trace.LogTrace;
import hello.advenced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if(itemId.equals(("ex"))) {
                    throw new IllegalArgumentException("예외 발생");
                }

                sleep(1000);
                return null;
            }
        };
        template.execute("OrderController.save()");
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
