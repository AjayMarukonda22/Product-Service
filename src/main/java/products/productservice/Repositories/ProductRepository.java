package products.productservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import products.productservice.models.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
