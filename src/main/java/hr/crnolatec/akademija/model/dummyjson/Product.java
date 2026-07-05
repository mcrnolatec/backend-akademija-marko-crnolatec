package hr.crnolatec.akademija.model.dummyjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(
        Long id,
        String title,
        String description,
        String category,
        BigDecimal price,
        BigDecimal discountPercentage,
        Double rating,
        Integer stock,
        List<String> tags,
        String brand,
        String sku,
        Integer weight,
        Dimensions dimensions,
        String warrantyInformation,
        String shippingInformation,
        String availabilityStatus,
        List<Review> reviews,
        String returnPolicy,
        Integer minimumOrderQuantity,
        Meta meta,
        List<String> images,
        String thumbnail
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Dimensions(
            BigDecimal width,
            BigDecimal height,
            BigDecimal depth
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Review(
            Integer rating,
            String comment,
            String date,
            String reviewerName,
            String reviewerEmail
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Meta(
            String createdAt,
            String updatedAt,
            String barcode,
            String qrCode
    ) {
    }
}
