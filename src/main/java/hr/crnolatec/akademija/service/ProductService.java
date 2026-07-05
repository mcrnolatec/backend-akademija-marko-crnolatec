package hr.crnolatec.akademija.service;

import hr.crnolatec.akademija.model.dto.ProductDTO;
import hr.crnolatec.akademija.model.dto.ProductDetailsDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();
    ProductDetailsDTO getProductById(Long id);
    List<ProductDTO> searchProductsByName(String name);
    List<ProductDTO> filterProducts(String category, Double minPrice, Double maxPrice);
}
