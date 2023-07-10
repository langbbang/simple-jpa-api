package me.songha.simple.order.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.member.dto.MemberResponse;
import me.songha.simple.order.entity.Order;
import me.songha.simple.order.entity.OrderStatus;
import me.songha.simple.orderproduct.dto.OrderProductResponse;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private MemberResponse memberResponse;
    private  List<OrderProductResponse> orderProductResponses = new ArrayList<>();
    private OrderStatus orderStatus;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    @Builder
    public OrderResponse(Order order) {
        this.id = order.getId();
        this.memberResponse = order.getMember().toMemberResponse();
//        this.orderProductResponses = order.getOrderProduct();
//        this.orderStatus = order.getOrderLogs().
        this.createAt = order.getCreateAt();
        this.updateAt = order.getUpdateAt();
    }
}
