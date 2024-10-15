package products.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import products.productservice.Repositories.ProductRepository;
import products.productservice.dtos.GenericProductDto;
import products.productservice.models.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service
public class SearchService {
    private ProductRepository productRepository;
    @Autowired
    public SearchService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<GenericProductDto> search(String title, int pageNumber, int pageSize) {
        Sort sort = Sort.by("title")
                .and(Sort.by("price").descending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepository.findAllByTitleContaining(title, pageable);
          List<Product> productList = productPage.get().toList();
          List<GenericProductDto> genericProductDtos = new ArrayList<>();
          for(Product product : productList) {
              GenericProductDto genericProductDto = new GenericProductDto();
              genericProductDto.setPrice(product.getPrice());
              genericProductDto.setTitle(product.getTitle());
              genericProductDto.setImage(product.getImage());
              genericProductDto.setDescription(product.getDescription());
              genericProductDtos.add(genericProductDto);
          }
          Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(genericProductDtos, productPage.getPageable(), productPage.getTotalElements());
          return genericProductDtoPage;
    }

}
