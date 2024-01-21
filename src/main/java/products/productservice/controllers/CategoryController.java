package products.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import products.productservice.dtos.GenericCategoryDto;
import products.productservice.dtos.GenericProductDto;
import products.productservice.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
private CategoryService categoryService;

@Autowired
public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
}
@GetMapping("/{category}")
public List<GenericProductDto> getInCategory(@PathVariable("category") String category) {
    return categoryService.getProductsByCategory(category);
}
@GetMapping
    public List<String> getAllCategories() {

    return categoryService.getAllCategories();
}

}
