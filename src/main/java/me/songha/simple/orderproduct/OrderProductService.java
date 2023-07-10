package me.songha.simple.orderproduct;

import lombok.RequiredArgsConstructor;
import me.songha.simple.common.exception.CommonException;
import me.songha.simple.orderproduct.dto.OrderProductCreationReq;
import me.songha.simple.orderproduct.dto.OrderProductResponse;
import me.songha.simple.orderproduct.entity.OrderProduct;
import me.songha.simple.product.ProductRepository;
import me.songha.simple.product.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public OrderProductResponse save(OrderProductCreationReq req) {
        Product product = productRepository.getReferenceById(req.getProductId());

        return orderProductRepository.save(OrderProduct.builder()
                .req(req)
                .product(product)
                .build()).toResponse();
    }

    public OrderProductResponse findOrderProductById(Long id) {
        return orderProductRepository.findById(id).orElseThrow(CommonException::new).toResponse();
    }
}
