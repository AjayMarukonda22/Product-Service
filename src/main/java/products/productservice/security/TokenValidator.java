package products.productservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;
    private String validateTokenUrl = "https://localhost:8080/auth/validate";
    @Autowired
    public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JwtData> validateToken(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JwtData> response = restTemplate.getForEntity(validateTokenUrl, JwtData.class, token);
        JwtData jwtData = response.getBody();
        return Optional.of(jwtData);
    }
}
