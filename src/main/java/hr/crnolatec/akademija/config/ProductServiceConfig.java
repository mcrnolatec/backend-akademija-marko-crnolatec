package hr.crnolatec.akademija.config;

import hr.crnolatec.akademija.service.ProductService;
import hr.crnolatec.akademija.service.dummyjson.DummyJsonProductClient;
import hr.crnolatec.akademija.service.dummyjson.ProductServiceDummyJSON;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

    @Bean
    @ConditionalOnProperty(name = "app.product-service", havingValue = "dummyjson", matchIfMissing = true)
    public ProductService productService(DummyJsonProductClient dummyJsonProductClient) {
        return new ProductServiceDummyJSON(dummyJsonProductClient);
    }
}
