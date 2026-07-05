package hr.crnolatec.akademija.service.dummyjson;

import hr.crnolatec.akademija.model.dummyjson.DummyJsonProductsResponse;
import hr.crnolatec.akademija.model.dummyjson.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DummyJsonProductClient {

    private static final Logger log = Logger.getLogger(DummyJsonProductClient.class.getName());
    private static final String BASE_URL = "https://dummyjson.com";

    private final RestClient restClient = RestClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Cacheable("dummyjsonProducts")
    public List<Product> fetchAllProducts() {
        try {
            DummyJsonProductsResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/products")
                            .queryParam("limit", 0)
                            .build())
                    .retrieve()
                    .body(DummyJsonProductsResponse.class);

            if (response == null || response.products() == null) {
                log.log(Level.SEVERE, "Received empty response from DummyJSON");
                return List.of();
            }

            log.info("Received " + response.products().size() + " products from DummyJSON");
            return response.products();
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to fetch products from DummyJSON", ex);
            return List.of();
        }
    }
}
