package products.productservice.thirdPartyClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import products.productservice.Repositories.ProductRepository;
import products.productservice.models.Orders;
import products.productservice.models.Product;

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
        List<Orders> orders = product.getOrders();
        if (orders != null) {
            for (Orders order : orders) {
                order.getProductList().add(product);
            }
        }
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
