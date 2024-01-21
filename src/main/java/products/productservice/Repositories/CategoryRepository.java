package products.productservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import products.productservice.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = "select * from Category where Category.name = :name", nativeQuery = true)
    public List<Category> findCategoryByName(String name);
    @Query(value = "select DISTINCT Category.name from Category", nativeQuery = true)
      public List<String> getAllCategories();
}
