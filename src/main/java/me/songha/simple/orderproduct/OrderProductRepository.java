package me.songha.simple.orderproduct;

import me.songha.simple.orderproduct.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
