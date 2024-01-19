package products.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import products.productservice.models.Category;

@Getter
@Setter

public class FakeStoreProductDto {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
