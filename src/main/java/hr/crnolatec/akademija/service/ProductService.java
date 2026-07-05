package hr.crnolatec.akademija.service;

import hr.crnolatec.akademija.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();
}
