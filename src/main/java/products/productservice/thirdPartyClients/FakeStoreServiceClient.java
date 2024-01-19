package products.productservice.thirdPartyClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.FakeStoreProductDto;
import products.productservice.dtos.GenericProductDto;

import java.util.List;
@Component

public class FakeStoreServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductById;
    private String ProductUrl;
    @Autowired
    public FakeStoreServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.api.BaseUrl}") String  FakeStoreBaseUrl, @Value("${fakeStore.api.product}") String FakeStoreProductPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.getProductById = FakeStoreBaseUrl + FakeStoreProductPath + "/{id}";
        this.ProductUrl = FakeStoreBaseUrl + FakeStoreProductPath;
    }

    public FakeStoreProductDto getproductbyId(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(getProductById, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto == null) {
            throw new NotFoundException("The Product with " + id + " not found.");
        }
        return fakeStoreProductDto;

    }
    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(ProductUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDto>>() {});

        List<FakeStoreProductDto> fakeStoreProduct = response.getBody();
        return fakeStoreProduct;
    }
    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(ProductUrl, genericProductDto, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;


    }


    public FakeStoreProductDto updateProduct(String id, GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // HttpHeaders headers = new HttpHeaders();
        HttpEntity<GenericProductDto> entity = new HttpEntity<GenericProductDto>(genericProductDto);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.exchange(getProductById, HttpMethod.PUT, entity, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto deleteProduct(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<GenericProductDto> entity = new HttpEntity<>(headers);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.exchange(getProductById,HttpMethod.DELETE, entity, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
}
