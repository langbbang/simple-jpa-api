package me.songha.simple.orderproduct.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.songha.simple.order.entity.Order;
import me.songha.simple.orderproduct.dto.OrderProductCreationReq;
import me.songha.simple.orderproduct.dto.OrderProductResponse;
import me.songha.simple.product.entity.Product;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_product")
@Entity
public class OrderProduct {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "product_id")
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    private Product product;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    private Order order;

    @Column
    private Integer quantity;

    @Column
    private Integer price;

    @CreatedDate
    @Column(updatable = false)
    @NotNull
    private ZonedDateTime createAt;

    @LastModifiedDate
    private ZonedDateTime updateAt;

    @Builder
    public OrderProduct(OrderProductCreationReq req, Product product, Order order) {
        this.order = order;
        this.product = product;
        this.quantity = req.getQuantity();
        this.price = req.getPrice();
    }

    public OrderProductResponse toResponse() {
        return OrderProductResponse.builder().orderProduct(this).build();
    }
}
