package me.songha.simple.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductUpdateRequest {
    @NotNull
    private Long id;
    private String name;
    private Integer price;
    private String description;

    @Builder
    public ProductUpdateRequest(Long id, String name, Integer price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
