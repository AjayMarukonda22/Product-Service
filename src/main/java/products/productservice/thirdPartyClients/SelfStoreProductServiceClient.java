package products.productservice.thirdPartyClients;

import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.Repositories.ProductRepository;
import products.productservice.models.Orders;
import products.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component

public class SelfStoreProductServiceClient {
    private ProductRepository productRepository;
    @Autowired
    public SelfStoreProductServiceClient(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getproductbyId(UUID uuid)  {
        Optional<Product> product = productRepository.findById(uuid);
            return product;

    }
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }


    public void createProduct(Product product) {
        Orders order = new Orders();
        List<Orders> orderList = new ArrayList<>();
        orderList.add(order);
        product.setOrders(orderList);
        productRepository.save(product);
    }

    public Product updateProduct(UUID uuid, Product product) {
     Optional<Product> product1 = productRepository.findById(uuid);
     Product product2 = product1.get();
     product2.setPrice(product.getPrice());
     product2.setImage(product.getImage());
     productRepository.save(product2);
     return product2;
    }

    public void deleteProduct(UUID uuid) {
        productRepository.deleteById(uuid);
    }
}
