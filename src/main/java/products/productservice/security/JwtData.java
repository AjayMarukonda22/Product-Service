package products.productservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtData {
    private String email;
    private List<String > Roles;
}
