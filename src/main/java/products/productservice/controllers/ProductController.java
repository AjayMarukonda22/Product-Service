package products.productservice.controllers;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;
import products.productservice.security.JwtData;
import products.productservice.security.TokenValidator;
import products.productservice.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private TokenValidator tokenValidator;
    private RestTemplate restTemplate;
    @Autowired
    public ProductController(ProductService productService, TokenValidator tokenValidator, RestTemplate restTemplate) {
        this.productService = productService;
        this.tokenValidator  = tokenValidator;
        this.restTemplate = restTemplate;
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(
            @Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @PathVariable("id") String id) throws NotFoundException {
//        Optional<JwtData> optionalJwtData = tokenValidator.validateToken(authToken);
        Object userdata = restTemplate.getForEntity("http://userservice/users/1", Object.class);

        GenericProductDto genericProductDto = productService.getproductbyId(id);
        return genericProductDto;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping
    public Optional<GenericProductDto> createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);

    }
    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable("id") String id, @RequestBody GenericProductDto genericProductDto) {
        return productService.updateProduct(id, genericProductDto);
    }
    @DeleteMapping("{id}")
    public Optional<GenericProductDto> deleteProduct(@PathVariable("id") String id) throws NotFoundException{
        return productService.deleteProduct(id);
    }
}
