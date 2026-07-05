package hr.crnolatec.akademija.service;

import hr.crnolatec.akademija.model.dto.ProductDTO;
import hr.crnolatec.akademija.model.dto.ProductDetailsDTO;
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
            return fetchAllProducts().stream()
                    .map(this::toProductDto)
                    .toList();
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to fetch products from DummyJSON", ex);
            return List.of();
        }
    }

    @Override
    public ProductDetailsDTO getProductById(Long id) {
        try {
            Product response = restClient.get()
                    .uri("/products/{id}", id)
                    .retrieve()
                    .body(Product.class);

            if (response == null) {
                return null;
            }

            return toProductDetailsDTO(response);
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to fetch product with id " + id + " from DummyJSON", ex);
            return null;
        }
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        try {
            DummyJsonProductsResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/products/search")
                            .queryParam("q", name)
                            .build())
                    .retrieve()
                    .body(DummyJsonProductsResponse.class);

            if (response == null || response.products() == null) {
                log.warning("Received empty search response from DummyJSON for query: " + name);
                return List.of();
            }

            log.info("Received " + response.products().size() + " search results from DummyJSON for query: " + name);

            return response.products().stream()
                    .map(this::toProductDto)
                    .toList();
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to search products from DummyJSON for query: " + name, ex);
            return List.of();
        }
    }

    @Override
    public List<ProductDTO> filterProducts(String category, Double minPrice, Double maxPrice) {
        try {
            List<Product> products = fetchAllProducts();
            if (products.isEmpty()) {
                return List.of();
            }

            List<Product> filtered = products.stream()
                    .filter(product -> category == null || category.isBlank() || category.equalsIgnoreCase(product.category()))
                    .filter(product -> minPrice == null || (product.price() != null && product.price().doubleValue() >= minPrice))
                    .filter(product -> maxPrice == null || (product.price() != null && product.price().doubleValue() <= maxPrice))
                    .toList();

            log.info("Filter returned " + filtered.size() + " products from DummyJSON");

            return filtered.stream()
                    .map(this::toProductDto)
                    .toList();
        } catch (RestClientException ex) {
            log.log(Level.SEVERE, "Failed to filter products from DummyJSON", ex);
            return List.of();
        }
    }

    private List<Product> fetchAllProducts() {
        DummyJsonProductsResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/products")
                        .queryParam("limit", 0)
                        .build())
                .retrieve()
                .body(DummyJsonProductsResponse.class);

        if (response == null || response.products() == null) {
            log.warning("Received empty response from DummyJSON");
            return List.of();
        }

        log.info("Received " + response.products().size() +  " products from DummyJSON");
        return response.products();
    }

    private ProductDTO toProductDto(Product product) {
        String description = product.description();
        if (description != null && description.length() > 100) {
            description = description.substring(0, 100);
        }

        String image = product.images() != null && !product.images().isEmpty()
                ? product.images().getFirst()
                : product.thumbnail();

        return new ProductDTO(image, product.title(), product.price() != null ? product.price().doubleValue() : null, description);
    }

    private ProductDetailsDTO toProductDetailsDTO(Product product) {
        String image = product.images() != null && !product.images().isEmpty()
                ? product.images().getFirst()
                : product.thumbnail();

        return new ProductDetailsDTO(
                product.id(),
                product.title(),
                product.description(),
                image,
                product.price() != null ? product.price().doubleValue() : null,
                product.category(),
                product.brand(),
                product.stock()
        );
    }
}
