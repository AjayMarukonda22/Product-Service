package products.productservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import products.productservice.models.Orders;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {

}
