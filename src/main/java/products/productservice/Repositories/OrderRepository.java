package products.productservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import products.productservice.models.Orders;

import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {

}
