package products.productservice.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import products.productservice.Exceptions.NotFoundException;
import products.productservice.dtos.GenericProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public GenericProductDto getproductbyId(String id) throws NotFoundException;
    public List<GenericProductDto> getAllProducts();
    public  Optional<GenericProductDto> createProduct(GenericProductDto genericProductDto);
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto);
    public Optional<GenericProductDto> deleteProduct(String id) throws NotFoundException;
}
