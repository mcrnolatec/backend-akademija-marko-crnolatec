package hr.crnolatec.akademija.model.dummyjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DummyJsonProductsResponse(
        List<Product> products,
        int total,
        int skip,
        int limit
) {}
