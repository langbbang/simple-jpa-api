package me.songha.simple.product.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.product.entity.Product;

@Data
@Builder
public class ProductCreationRequest {
    private String name;
    private int price;
    private String description;

    public Product toEntity() {
        return Product.builder()
                .description(description)
                .price(price)
                .name(name)
                .build();
    }
}
