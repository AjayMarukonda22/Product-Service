package products.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;
import products.productservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreService") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.getproductbyId(id);
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);

    }
    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable String id, @RequestBody GenericProductDto genericProductDto) {
        return productService.updateProduct(id, genericProductDto);
    }
    @DeleteMapping("{id}")
    public GenericProductDto deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
