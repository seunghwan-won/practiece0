package hello.proxy.app.v2;

public class OrderServiceV2 {

    private final OrderRepositoryV2 repositoryV2;

    public OrderServiceV2(OrderRepositoryV2 repositoryV2) {
        this.repositoryV2 = repositoryV2;
    }

    public void save(String itemId) {
        repositoryV2.save(itemId);
    }
}
