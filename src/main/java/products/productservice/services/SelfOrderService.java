package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import products.productservice.models.Orders;
import products.productservice.thirdPartyClients.SelfOrderServiceClient;

@Primary
@Service("SelfOrderService")
public class SelfOrderService implements OrderService {
    private SelfOrderServiceClient selfOrderServiceClient;
    @Autowired
    public SelfOrderService(SelfOrderServiceClient selfOrderServiceClient) {
        this.selfOrderServiceClient = selfOrderServiceClient;
    }

    @Override
    public void createOrder(Orders order) {
        selfOrderServiceClient.createOrder(order);
    }
}
