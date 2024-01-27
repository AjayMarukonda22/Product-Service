package products.productservice.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.FakeStoreProductDto;
import products.productservice.dtos.GenericProductDto;
import products.productservice.thirdPartyClients.FakeStoreProductServiceClient;
import products.productservice.thirdPartyClients.FakeStoreProductServiceClientTest;
import products.productservice.thirdPartyClients.SelfOrderServiceClient;
import products.productservice.thirdPartyClients.SelfStoreProductServiceClient;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest

public class ProductServiceTest {
    @Autowired
    private FakeStoreProductService fakeStoreProductService;
    @Autowired
    private  SelfStoreProductService selfStoreProductService;
    @MockBean
    private SelfStoreProductServiceClient selfStoreProductServiceClientMock;
    @MockBean
    private FakeStoreProductServiceClient fakeStoreProductServiceClientMock;

    @Test
    public void testGetAllProductsReturnCorrectResponse() {
        List<FakeStoreProductDto> expectedResponse = new ArrayList<FakeStoreProductDto>();
        when(fakeStoreProductServiceClientMock.getAllProducts())
                .thenReturn(expectedResponse);
      List<GenericProductDto> response = fakeStoreProductService.getAllProducts();
        Assertions.assertEquals(expectedResponse, response);
    }
    @Test
    public void testGetProductByIdReturnExceptionWhenProductIsNotFound() throws NotFoundException{
        when(selfStoreProductServiceClientMock.getproductbyId(any(UUID.class)))
                .thenReturn(null);
        Assertions.assertThrows(NotFoundException.class, () -> selfStoreProductService.getproductbyId("b21c967c-d868-47bc-94e4-eeeff0d36f22"));
    }
}
