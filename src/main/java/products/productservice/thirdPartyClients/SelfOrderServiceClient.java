package products.productservice.thirdPartyClients;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import products.productservice.Repositories.OrderRepository;
import products.productservice.models.Orders;
import products.productservice.models.Product;

import java.util.List;

@Component

public class SelfOrderServiceClient {
    private OrderRepository orderRepository;

    @Autowired
    public SelfOrderServiceClient(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Transactional
    public void createOrder(Orders order) {
        List<Product> productList = order.getProductList();
        if (productList != null) {
            for (Product product : productList) {
                product.getOrders().add(order);
            }
        }
        orderRepository.save(order);
    }
}
