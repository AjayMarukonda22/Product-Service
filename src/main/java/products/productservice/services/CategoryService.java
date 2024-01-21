package products.productservice.services;

import org.springframework.data.jpa.repository.Query;
import products.productservice.dtos.GenericCategoryDto;
import products.productservice.dtos.GenericProductDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public List<GenericProductDto> getProductsByCategory(String category);
    public List<String> getAllCategories();
}
