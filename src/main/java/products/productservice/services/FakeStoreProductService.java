package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.FakeStoreProductDto;
import products.productservice.dtos.GenericProductDto;
import products.productservice.thirdPartyClients.FakeStoreServiceClient;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {
    private FakeStoreServiceClient fakeStoreServiceClient;
    @Autowired
    public FakeStoreProductService(FakeStoreServiceClient fakeStoreServiceClient) {
        this.fakeStoreServiceClient =  fakeStoreServiceClient;
    }
    @Override
    public GenericProductDto getproductbyId(String id) throws NotFoundException {

        return convertToGenericProductDto( fakeStoreServiceClient.getproductbyId(id));

    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProduct = fakeStoreServiceClient.getAllProducts();
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
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        return convertToGenericProductDto(fakeStoreServiceClient.createProduct(genericProductDto));

    }

    @Override
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto) {

        return convertToGenericProductDto(fakeStoreServiceClient.updateProduct(id, genericProductDto));
    }

    @Override
    public GenericProductDto deleteProduct(String id) {

        return convertToGenericProductDto(fakeStoreServiceClient.deleteProduct(id));
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
