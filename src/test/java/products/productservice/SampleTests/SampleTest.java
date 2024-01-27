package products.productservice.SampleTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class SampleTest {
    @Test
    public void test() {
        Random random = new Random();
        int result = random.nextInt(100);
        assert (result < 100);
    }
    @Test
    public void getResult() {
        int result = add(1, 2);
        Assertions.assertEquals(3, result, "Result should be 3");
    }
    public int add(int a , int b) {
        return a+b;
    }
}
