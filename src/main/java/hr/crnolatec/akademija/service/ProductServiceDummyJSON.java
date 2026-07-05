package hr.crnolatec.akademija.service;

import hr.crnolatec.akademija.model.dto.ProductDTO;
import hr.crnolatec.akademija.model.dummyjson.DummyJsonProductsResponse;
import hr.crnolatec.akademija.model.dummyjson.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductServiceDummyJSON implements ProductService {

    private static final Logger log = Logger.getLogger(ProductServiceDummyJSON.class.getName());
    private static final String BASE_URL = "https://dummyjson.com";

    private final RestClient restClient = RestClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public List<ProductDTO> getProducts() {
        try {
            DummyJsonProductsResponse response = restClient.get()
                    .uri("/products")
                    .retrieve()
                    .body(DummyJsonProductsResponse.class);

            if (response == null || response.products() == null) {
                log.warning("Received empty product response from DummyJSON");
                return List.of();
            }

            log.info("Received " + response.products().size() + " products from DummyJSON");

            return response.products().stream()
                    .map(this::toDto)
                    .toList();
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to fetch products from DummyJSON", ex);
            return List.of();
        }
    }

    private ProductDTO toDto(Product product) {
        String description = product.description();
        if (description != null && description.length() > 100) {
            description = description.substring(0, 100);
        }

        String image = product.images() != null && !product.images().isEmpty()
                ? product.images().getFirst()
                : product.thumbnail();

        return new ProductDTO(image, product.title(), product.price() != null ? product.price().doubleValue() : null, description);
    }
}
