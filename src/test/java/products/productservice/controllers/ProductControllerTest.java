package products.productservice.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;
import products.productservice.services.ProductService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest

public class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productServiceMock;
//    public ProductControllerTest() {
//        this.productServiceMock = Mockito.mock(ProductService.class);
//        this.productController = new ProductController(productServiceMock);
//    }
@Test
    public void testGetproductbyIdReturnCorrectResponse() throws NotFoundException {
       when(productServiceMock.getproductbyId(any(String.class)))
               .thenReturn(new GenericProductDto());
        GenericProductDto response = productController.getProductById("11");
    Assertions.assertNotNull(response);
    }
}
