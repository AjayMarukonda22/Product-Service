package products.productservice.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import products.productservice.models.Product;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
   Page<Product> findAllByTitleContaining(String title, Pageable pageable);
}
