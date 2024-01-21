package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import products.productservice.dtos.FakeStoreCategoryDto;
import products.productservice.dtos.FakeStoreProductDto;
import products.productservice.dtos.GenericCategoryDto;
import products.productservice.dtos.GenericProductDto;
import products.productservice.models.Category;
import products.productservice.thirdPartyClients.FakeStoreCategoryServiceClient;
import products.productservice.thirdPartyClients.FakeStoreProductServiceClient;

import java.util.ArrayList;
import java.util.List;
@Service("FakeStoreCategoryService")

public class FakeStoreCategoryService implements CategoryService{
    private FakeStoreCategoryServiceClient fakeStoreCategoryServiceClient;
    @Autowired
    public FakeStoreCategoryService(FakeStoreCategoryServiceClient fakeStoreCategoryServiceClient) {
        this.fakeStoreCategoryServiceClient = fakeStoreCategoryServiceClient;
    }

    @Override
    public List<GenericProductDto> getProductsByCategory(String category) {
        List<FakeStoreProductDto> fakeStoreProduct = fakeStoreCategoryServiceClient.getProductsByCategory(category);
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
    public List<String> getAllCategories() {
        List<String> fakeStoreCategoryList = fakeStoreCategoryServiceClient.getAllCategories();
        return fakeStoreCategoryList;
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
    public GenericCategoryDto convertToGenericCategoryDto(FakeStoreCategoryDto fakeStoreCategoryDto) {
        GenericCategoryDto genericCategoryDto = new GenericCategoryDto();
        genericCategoryDto.setName(fakeStoreCategoryDto.getName());
        return genericCategoryDto;
    }
}

