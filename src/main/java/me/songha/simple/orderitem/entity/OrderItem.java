package me.songha.simple.orderitem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
@Table(name = "order_item")
@Entity
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer quantity;

    private Integer price;

    @CreatedDate
    @Column(updatable = false)
    @NotNull
    private ZonedDateTime createAt;

    @LastModifiedDate
    private ZonedDateTime updateAt;

}
