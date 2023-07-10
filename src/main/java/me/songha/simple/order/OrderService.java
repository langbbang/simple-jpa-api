package me.songha.simple.order;

import lombok.RequiredArgsConstructor;
import me.songha.simple.common.exception.CommonException;
import me.songha.simple.order.dto.OrderResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderResponse findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(CommonException::new).toOrderResponse();
    }
}
