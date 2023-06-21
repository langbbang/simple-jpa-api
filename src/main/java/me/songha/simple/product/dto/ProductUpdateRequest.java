package me.songha.simple.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductUpdateRequest {
    @NotNull
    private Long id;
    private String name;
    private Integer price;
    private String description;
}
