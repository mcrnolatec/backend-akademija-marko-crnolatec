package hr.crnolatec.akademija.model.dto;

public record ProductDetailsDTO(
        Long id,
        String name,
        String description,
        String image,
        Double price,
        String category,
        String brand,
        Integer stock
) {
}
