package hello.proxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1 {

    private final OrderServiceV1 serviceV1;

    public OrderControllerV1Impl(OrderServiceV1 serviceV1) {
        this.serviceV1 = serviceV1;
    }

    @Override
    public String request(String itemId) {
        serviceV1.save(itemId);
        return "ok";
    }

    @Override
    public String nolog() {
        return "ok";
    }
}
