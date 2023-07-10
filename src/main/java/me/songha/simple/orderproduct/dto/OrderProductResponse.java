package me.songha.simple.orderproduct.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.orderproduct.entity.OrderProduct;
import me.songha.simple.product.dto.ProductResponse;

import java.time.ZonedDateTime;

@Data
public class OrderProductResponse {
    private Long id;
    private ProductResponse product;
    private Integer quantity;
    private Integer price;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    @Builder
    public OrderProductResponse(OrderProduct orderProduct) {
        this.id = orderProduct.getId();
        this.product = orderProduct.getProduct().toProductResponse();
        this.quantity = orderProduct.getQuantity();
        this.price = orderProduct.getPrice();
        this.createAt = orderProduct.getCreateAt();
        this.updateAt = orderProduct.getUpdateAt();
    }
}
