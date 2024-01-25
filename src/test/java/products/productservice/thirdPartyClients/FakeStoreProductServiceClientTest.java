package products.productservice.thirdPartyClients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.FakeStoreProductDto;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FakeStoreProductServiceClientTest {
    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    private RestTemplateBuilder restTemplateBuilder = Mockito.mock(RestTemplateBuilder.class);
    @Test
    public void testGetproductbyIdProductNotFoundReturnNull() throws NotFoundException {

    }
}
