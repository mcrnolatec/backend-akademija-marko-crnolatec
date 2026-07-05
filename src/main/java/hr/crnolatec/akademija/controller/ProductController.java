package hr.crnolatec.akademija.controller;

import hr.crnolatec.akademija.model.dto.ProductDTO;
import hr.crnolatec.akademija.model.dto.ProductDetailsDTO;
import hr.crnolatec.akademija.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDetailsDTO getProductById(@PathVariable Long id) {
        ProductDetailsDTO product = productService.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(NOT_FOUND, "Product not found");
        }
        return product;
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    @GetMapping("/filter")
    public List<ProductDTO> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return productService.filterProducts(category, minPrice, maxPrice);
    }
}
