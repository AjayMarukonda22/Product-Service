package products.productservice.thirdPartyClients;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import products.productservice.Repositories.CategoryRepository;
import products.productservice.models.Category;
import products.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component

public class SelfStoreCategoryServiceClient {
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfStoreCategoryServiceClient(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Product> getInCategory(String name) {
       List<Category> categories = categoryRepository.findCategoryByName(name);
       List<Product> productList = new ArrayList<>();
       for(Category category : categories) {
           for(Product product : category.getProducts()) {
               productList.add(product);
           }
       }
       return productList;
}
    public List<String> getAllCategories() {
        List<String> categories = categoryRepository.getAllCategories();
        return categories;
    }
 }
