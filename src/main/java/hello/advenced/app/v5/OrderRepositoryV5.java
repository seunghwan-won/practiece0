package hello.advenced.app.v5;

import hello.advenced.app.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public void save(String itemId) {
        template.execute("OrderRepository.save()",() -> {
            if(itemId.equals(("ex"))) {
                throw new IllegalArgumentException("예외 발생");
            }

            sleep(1000);
            return null;
        });
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
