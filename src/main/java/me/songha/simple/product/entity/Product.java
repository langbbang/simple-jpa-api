package me.songha.simple.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.songha.simple.product.dto.ProductResponse;
import me.songha.simple.product.dto.ProductUpdateRequest;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Size(min = 2, max = 64)
    @Column(length = 128, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer price;

    @Size(max = 100)
    @Column(length = 255)
    private String description;

    @NotNull
    @Column(updatable = false)
    @CreatedDate
    private ZonedDateTime createAt;

    @Column(insertable = false) // insert 시 null
    @LastModifiedDate
    private ZonedDateTime updateAt;

    @Builder
    public Product(Long id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductResponse toProductResponse() {
        return ProductResponse.builder().product(this).build();
    }

    public Product updateProduct(ProductUpdateRequest updateRequest) {
        if (updateRequest.getName() != null)
            this.name = updateRequest.getName();

        if (updateRequest.getPrice() != null)
            this.price = updateRequest.getPrice();

        if (updateRequest.getDescription() != null)
            this.description = updateRequest.getDescription();

        return this;
    }
}
