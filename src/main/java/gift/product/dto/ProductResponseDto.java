package gift.product.dto;

import gift.product.entity.Product;

public record ProductResponseDto(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
            product.getId(),
            product.getName()
                .getValue(),
            product.getPrice(),
            product.getImageUrl()
        );
    }
}
