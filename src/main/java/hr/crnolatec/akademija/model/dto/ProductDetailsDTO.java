package hr.crnolatec.akademija.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detailed product view")
public record ProductDetailsDTO(
        @Schema(description = "Product identifier")
        Long id,
        @Schema(description = "Product name")
        String name,
        @Schema(description = "Full product description")
        String description,
        @Schema(description = "Primary product image")
        String image,
        @Schema(description = "Product price")
        Double price,
        @Schema(description = "Product category")
        String category,
        @Schema(description = "Product brand")
        String brand,
        @Schema(description = "Current stock count")
        Integer stock
) {
}
