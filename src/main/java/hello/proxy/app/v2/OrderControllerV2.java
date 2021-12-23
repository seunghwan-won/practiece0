package hello.proxy.app.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping
@ResponseBody
public class OrderControllerV2 {

    private final OrderServiceV2 serviceV2;

    public OrderControllerV2(OrderServiceV2 serviceV2) {
        this.serviceV2 = serviceV2;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        serviceV2.save(itemId);
        return "ok";
    }

    public String nolog() {
        return "ok";
    }
}
