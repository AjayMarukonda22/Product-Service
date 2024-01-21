package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import products.productservice.dtos.FakeStoreProductDto;
import products.productservice.dtos.GenericProductDto;
import products.productservice.models.Product;
import products.productservice.thirdPartyClients.SelfStoreCategoryServiceClient;
import products.productservice.thirdPartyClients.SelfStoreProductServiceClient;

import java.util.ArrayList;
import java.util.List;
@Primary
@Service("SelfStoreCategoryService")

public class SelfStoreCategoryService implements CategoryService{
    private SelfStoreCategoryServiceClient selfStoreCategoryServiceClient;
    @Autowired
    public SelfStoreCategoryService(SelfStoreCategoryServiceClient selfStoreCategoryServiceClient) {
        this.selfStoreCategoryServiceClient = selfStoreCategoryServiceClient;
    }

    @Override
    public List<GenericProductDto> getProductsByCategory(String category) {
      List<Product> productList = selfStoreCategoryServiceClient.getInCategory(category);
      int size = productList.size();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = productList.get(i);
            GenericProductDto genericProductDto = convertToGenericProductDto(product);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public List<String> getAllCategories() {
        return selfStoreCategoryServiceClient.getAllCategories();
    }
    public GenericProductDto convertToGenericProductDto (Product product) {
        GenericProductDto newGenericProductDto = new GenericProductDto();
        newGenericProductDto.setId(String.valueOf(product.getUuid()));
        newGenericProductDto.setTitle(product.getTitle());
        newGenericProductDto.setDescription(product.getDescription());
        newGenericProductDto.setCategory(product.getCategory().getName());
        newGenericProductDto.setImage(product.getImage());
        newGenericProductDto.setPrice(product.getPrice());
        return newGenericProductDto;
    }
}
