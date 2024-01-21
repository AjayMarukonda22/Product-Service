package products.productservice.dtos;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
public class GenericCategoryDto {
    String name;
    List<GenericProductDto> productDtoList;
}
