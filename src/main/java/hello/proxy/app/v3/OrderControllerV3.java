package hello.proxy.app.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderControllerV3 {

    private final OrderServiceV3 serviceV3;

    public OrderControllerV3(OrderServiceV3 serviceV3) {
        this.serviceV3 = serviceV3;
    }

    @GetMapping("/v3/request")
    public String request(String itemId) {
        serviceV3.save(itemId);
        return "ok";
    }

    @GetMapping("/v3/no-log")
    public String nolog() {
        return "ok";
    }
}
