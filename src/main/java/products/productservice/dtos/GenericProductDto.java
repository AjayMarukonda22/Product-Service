package products.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import products.productservice.models.Category;
import products.productservice.models.Orders;

import java.io.Serializable;
import java.util.List;

@Getter
 @Setter
public class GenericProductDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
    private List<Orders> orders;
}
