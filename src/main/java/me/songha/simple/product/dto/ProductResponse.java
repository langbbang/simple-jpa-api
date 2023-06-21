package me.songha.simple.product.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.product.entity.Product;

import java.time.ZonedDateTime;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private int price;
    private String description;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    @Builder
    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.createAt = product.getCreateAt();
        this.updateAt = product.getUpdateAt();
    }
}
