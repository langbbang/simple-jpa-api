package me.songha.simple.orderproduct;

import me.songha.simple.orderproduct.dto.OrderProductCreationReq;
import me.songha.simple.orderproduct.dto.OrderProductResponse;
import me.songha.simple.orderproduct.entity.OrderProduct;
import me.songha.simple.product.ProductService;
import me.songha.simple.product.dto.ProductCreationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @BeforeEach
    void setUp() {
        System.out.println("set up!!");
        for (int i = 1; i <= 5; i++) {
            ProductCreationRequest product = ProductCreationRequest.builder()
                    .description("상세설명" + i)
                    .price(1000 + i)
                    .name("상품" + i)
                    .build();
            productService.create(product);
        }

        for (int i = 1; i <= 5; i++) {
            OrderProductCreationReq req = OrderProductCreationReq.builder()
                    .productId((long) i)
                    .price(i * 1000)
                    .quantity(i)
                    .build();

            orderProductService.save(req);
        }
    }

    @Test
    public void orderProductService가_Null이_아님() {
        Assertions.assertNotNull(orderProductService);
    }

    @Test
    void ID로_orderProduct_조회() {
        OrderProductResponse orderProduct = orderProductService.findOrderProductById(1L);
        System.out.println(orderProduct);
        Assertions.assertNotNull(orderProduct);
    }
}