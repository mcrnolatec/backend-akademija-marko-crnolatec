package hr.crnolatec.akademija.controller;

import hr.crnolatec.akademija.model.dto.ProductDTO;
import hr.crnolatec.akademija.model.dto.ProductDetailsDTO;
import hr.crnolatec.akademija.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Products", description = "Product endpoints")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "List of products",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))))
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product details by id")
    @ApiResponse(responseCode = "200", description = "Product details",
            content = @Content(schema = @Schema(implementation = ProductDetailsDTO.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ProductDetailsDTO getProductById(@PathVariable Long id) {
        ProductDetailsDTO product = productService.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(NOT_FOUND, "Product not found");
        }
        return product;
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name")
    @ApiResponse(responseCode = "200", description = "Matching products",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))))
    public List<ProductDTO> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter products by category and price")
    @ApiResponse(responseCode = "200", description = "Filtered products",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))))
    public List<ProductDTO> filterProducts(
            @Parameter(description = "Category name")
            @RequestParam(required = false) String category,
            @Parameter(description = "Minimum product price")
            @RequestParam(required = false) Double minPrice,
            @Parameter(description = "Maximum product price")
            @RequestParam(required = false) Double maxPrice
    ) {
        return productService.filterProducts(category, minPrice, maxPrice);
    }
}
