package hr.crnolatec.akademija.model.dto;

public record ProductDTO(
        String image,
        String name,
        Double price,
        String description
) {
}
