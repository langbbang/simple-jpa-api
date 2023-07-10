package me.songha.simple.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.songha.simple.member.entity.Member;
import me.songha.simple.order.dto.OrderResponse;
import me.songha.simple.orderproduct.entity.OrderProduct;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
@Entity
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderProduct> orderProduct = new ArrayList<>();

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // orphanRemoval = true -> 고아 객체 delete
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderLog> orderLogs = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    @NotNull
    private ZonedDateTime createAt;

    @LastModifiedDate
    private ZonedDateTime updateAt;

    public OrderResponse toOrderResponse() {
        return OrderResponse.builder().order(this).build();
    }
}
