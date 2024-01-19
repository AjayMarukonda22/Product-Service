package products.productservice.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    public GenericProductDto getproductbyId(String id) throws NotFoundException;
    public List<GenericProductDto> getAllProducts();
    public  GenericProductDto createProduct(GenericProductDto genericProductDto);
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto);
    public GenericProductDto deleteProduct(String id);
}
