package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.FakeStoreProductDto;
import products.productservice.dtos.GenericProductDto;
import products.productservice.thirdPartyClients.FakeStoreProductServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RedisTemplate redisTemplate;
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient, RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public GenericProductDto getproductbyId(String id) throws NotFoundException {
       GenericProductDto genericProductDto = (GenericProductDto) redisTemplate.opsForValue().get(id);
       if(genericProductDto != null)
           return  genericProductDto;


        GenericProductDto genericProductDto1 =  convertToGenericProductDto( fakeStoreProductServiceClient.getproductbyId(id));
          redisTemplate.opsForValue().set(id, genericProductDto1);
          return genericProductDto1;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProduct = fakeStoreProductServiceClient.getAllProducts();
        int size = fakeStoreProduct.size();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FakeStoreProductDto fakeStoreProductDto = fakeStoreProduct.get(i);
            GenericProductDto genericProductDto = convertToGenericProductDto(fakeStoreProductDto);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public Optional<GenericProductDto> createProduct(GenericProductDto genericProductDto) {

        GenericProductDto genericProductDto1 =  convertToGenericProductDto(fakeStoreProductServiceClient.createProduct(genericProductDto));
   return Optional.of(genericProductDto1);
    }

    @Override
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto) {

        return convertToGenericProductDto(fakeStoreProductServiceClient.updateProduct(id, genericProductDto));
    }

    @Override
    public Optional<GenericProductDto> deleteProduct(String id) throws NotFoundException {

        GenericProductDto genericProductDto = convertToGenericProductDto(fakeStoreProductServiceClient.deleteProduct(id));
        return Optional.of(genericProductDto);
    }
    public GenericProductDto convertToGenericProductDto (FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto newGenericProductDto = new GenericProductDto();
        newGenericProductDto.setId(fakeStoreProductDto.getId());
        newGenericProductDto.setTitle(fakeStoreProductDto.getTitle());
        newGenericProductDto.setDescription(fakeStoreProductDto.getDescription());
        newGenericProductDto.setCategory(fakeStoreProductDto.getCategory());
        newGenericProductDto.setImage(fakeStoreProductDto.getImage());
        newGenericProductDto.setPrice(fakeStoreProductDto.getPrice());
        return newGenericProductDto;
    }
}
