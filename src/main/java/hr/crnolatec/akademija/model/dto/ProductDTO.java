package hr.crnolatec.akademija.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Summary view of a product")
public record ProductDTO(
        @Schema(description = "Primary product image")
        String image,
        @Schema(description = "Product name")
        String name,
        @Schema(description = "Product price")
        Double price,
        @Schema(description = "Truncated product description")
        String description
) {
}
