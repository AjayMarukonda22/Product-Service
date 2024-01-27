package products.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;
import products.productservice.services.ProductService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
public class ProductControllerMockMvcTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testGetProductById() throws Exception {
        GenericProductDto expectedGenericProductDto = new GenericProductDto();
        expectedGenericProductDto.setId("1");
        expectedGenericProductDto.setCategory("Mobile");
        expectedGenericProductDto.setTitle("iphone");
        expectedGenericProductDto.setPrice(100000);
        when(productService.getproductbyId(any(String.class)))
                .thenReturn(expectedGenericProductDto);
        ResultActions resultActions =  mockMvc.perform(get("/products/1"))
                 .andExpect(status().is(200))
                .andExpect(content().json("{\"id\":\"1\",\"title\":\"iphone\",\"description\":null,\"image\":null,\"category\":\"Mobile\",\"price\":100000.0,\"orders\":null}"))
                .andExpect(jsonPath("$.id").value(1));

    String responseString = resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("{\"id\":\"1\",\"title\":\"iphone\",\"description\":null,\"image\":null,\"category\":\"Mobile\",\"price\":100000.0,\"orders\":null}", responseString);
        GenericProductDto genericProductDto = objectMapper.readValue(responseString, GenericProductDto.class);
        Assertions.assertEquals("1", genericProductDto.getId());
    }
    @Test
    public void getProductId2() throws NotFoundException {
        when(productService.getproductbyId(any(String.class)))
                .thenReturn(new GenericProductDto());
        GenericProductDto genericProductDto = productController.getProductById("1");
        verify(productService,times(1)).getproductbyId("1");
    }
}
