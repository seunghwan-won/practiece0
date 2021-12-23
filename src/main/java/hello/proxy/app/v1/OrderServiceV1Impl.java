package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1{

    private final OrderRepositoryV1 repositoryV1;

    public OrderServiceV1Impl(OrderRepositoryV1 repositoryV1) {
        this.repositoryV1 = repositoryV1;
    }

    @Override
    public void save(String itemId) {
        repositoryV1.save(itemId);
    }
}
