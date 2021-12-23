package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {

    private final OrderRepositoryV3 repositoryV3;

    public OrderServiceV3(OrderRepositoryV3 repositoryV3) {
        this.repositoryV3 = repositoryV3;
    }

    public void save(String itemId) {
        repositoryV3.save(itemId);
    }
}
