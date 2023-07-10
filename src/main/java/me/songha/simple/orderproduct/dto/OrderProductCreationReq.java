package me.songha.simple.orderproduct.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.orderproduct.entity.OrderProduct;

@Builder
@Data
public class OrderProductCreationReq {
    private Long productId;
    private Integer quantity;
    private Integer price;

    public OrderProduct toEntity() {
        return OrderProduct.builder().req(this).build();
    }
}
