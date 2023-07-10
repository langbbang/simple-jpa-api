package me.songha.simple.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_log")
@Entity
public class OrderLog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "order_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    private OrderStatus orderStatus;

    @CreatedDate
    @Column(updatable = false)
    @NotNull
    private ZonedDateTime createAt;

    @LastModifiedDate
    private ZonedDateTime updateAt;
}
