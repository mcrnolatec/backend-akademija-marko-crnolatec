package hr.crnolatec.akademija.service.dummyjson;

import hr.crnolatec.akademija.model.dto.ProductDTO;
import hr.crnolatec.akademija.model.dto.ProductDetailsDTO;
import hr.crnolatec.akademija.model.dummyjson.Product;
import hr.crnolatec.akademija.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceDummyJSON implements ProductService {

    private final DummyJsonProductClient dummyJsonProductClient;

    public ProductServiceDummyJSON(DummyJsonProductClient dummyJsonProductClient) {
        this.dummyJsonProductClient = dummyJsonProductClient;
    }

    @Override
    public List<ProductDTO> getProducts() {
        return dummyJsonProductClient.fetchAllProducts().stream()
                .map(this::toProductDto)
                .toList();
    }

    @Override
    public ProductDetailsDTO getProductById(Long id) {
        return dummyJsonProductClient.fetchAllProducts().stream()
                .filter(product -> id != null && id.equals(product.id()))
                .findFirst()
                .map(this::toProductDetailsDTO)
                .orElse(null);
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        return dummyJsonProductClient.fetchAllProducts().stream()
                .filter(product -> name == null || name.isBlank() || (product.title() != null && product.title().toLowerCase().contains(name.toLowerCase())))
                .map(this::toProductDto)
                .toList();
    }

    @Override
    public List<ProductDTO> filterProducts(String category, Double minPrice, Double maxPrice) {
        List<Product> products = dummyJsonProductClient.fetchAllProducts();
        if (products.isEmpty()) {
            return List.of();
        }

        List<Product> filtered = products.stream()
                .filter(product -> category == null || category.isBlank() || category.equalsIgnoreCase(product.category()))
                .filter(product -> minPrice == null || (product.price() != null && product.price().doubleValue() >= minPrice))
                .filter(product -> maxPrice == null || (product.price() != null && product.price().doubleValue() <= maxPrice))
                .toList();

        return filtered.stream()
                .map(this::toProductDto)
                .toList();
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
