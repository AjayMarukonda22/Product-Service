package products.productservice.thirdPartyClients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.FakeStoreProductDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FakeStoreProductServiceClientTest {
    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder ;
    @Test
    public void testGetproductbyIdWhenProductNotFoundReturnNull() throws NotFoundException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
      when(restTemplateBuilder.build())
              .thenReturn(restTemplate);
        ResponseEntity<FakeStoreProductDto> response = new ResponseEntity<>(null, HttpStatus.OK);
      when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDto.class), any(String.class)))
              .thenReturn(response);
      FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.getproductbyId("11");
      Assertions.assertNull(fakeStoreProductDto);
    }
}
