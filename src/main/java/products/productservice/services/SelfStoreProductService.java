package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;
import products.productservice.models.Category;
import products.productservice.models.Orders;
import products.productservice.models.Product;
import products.productservice.thirdPartyClients.SelfStoreProductServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("SelfStoreProductService")

public class SelfStoreProductService implements ProductService{
    private SelfStoreProductServiceClient selfStoreProductServiceClient;
    @Autowired
    public SelfStoreProductService(SelfStoreProductServiceClient selfStoreProductServiceClient) {
        this.selfStoreProductServiceClient = selfStoreProductServiceClient;
    }
    @Override
    public GenericProductDto getproductbyId(String id) throws NotFoundException {
        UUID uuid = UUID.fromString(id);
        Optional<Product> product = selfStoreProductServiceClient.getproductbyId(uuid);
            return product.map(this::convertProductToGenericProductDto)
                    .orElseThrow(() -> new NotFoundException("The product with " + id + " is not found"));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
       List<Product> products = selfStoreProductServiceClient.getAllProducts();
       int N = products.size();;
       List<GenericProductDto> genericProductDtoList = new ArrayList<>();
       for(Product product : products) {
           genericProductDtoList.add(convertProductToGenericProductDto(product));
       }
       return genericProductDtoList;
    }

    @Override
    public Optional<GenericProductDto> createProduct(GenericProductDto genericProductDto) {
       Product product = convertGenericProductDtoToProduct(genericProductDto);
       selfStoreProductServiceClient.createProduct(product);
     return null;
    }

    @Override
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto) {
        Product product = convertGenericProductDtoToProduct(genericProductDto);
        UUID uuid = UUID.fromString(id);
      Product product1 = selfStoreProductServiceClient.updateProduct(uuid, product);
        return convertProductToGenericProductDto(product1);
    }

    @Override
    public Optional<GenericProductDto> deleteProduct(String id) {
        UUID uuid = UUID.fromString(id);
        selfStoreProductServiceClient.deleteProduct(uuid);
        return null;
    }
    public Product convertGenericProductDtoToProduct(GenericProductDto genericProductDto) {
        Product product = new Product();
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        String category = genericProductDto.getCategory();
        Category category1 = new Category();
        category1.setName(category);
        product.setCategory(category1);
        product.setImage(genericProductDto.getImage());
        product.setPrice(genericProductDto.getPrice());
        List<Orders> orders = genericProductDto.getOrders() != null ? new ArrayList<>(genericProductDto.getOrders()) : null;
        product.setOrders(orders);

        return product;
    }
    public GenericProductDto convertProductToGenericProductDto(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setId(product.getUuid().toString());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setOrders(new ArrayList<>(product.getOrders()));
        return genericProductDto;
    }
}
