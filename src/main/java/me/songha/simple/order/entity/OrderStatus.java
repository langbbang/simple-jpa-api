package me.songha.simple.order.entity;

// 주문 상태 : 주문완료, 취소, 배송중, 배송완료
public enum OrderStatus {
    PAYMENT,
    CANCELED,
    DELIVERING,
    COMPLETED
}
