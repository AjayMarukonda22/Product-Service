package products.productservice.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;

@Getter
@Setter
public class NotFoundException extends Exception {
    String message;
    public NotFoundException (String message) {
        super(message);
    }
}
