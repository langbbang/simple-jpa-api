package me.songha.simple.product;

import me.songha.simple.product.dto.ProductCreationRequest;
import me.songha.simple.product.dto.ProductResponse;
import me.songha.simple.product.dto.ProductUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

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
    }

    @Test
    void ID로_Product_조회() {
        ProductResponse product = productService.findProductById(1L);
        System.out.println(product);
        Assertions.assertNotNull(product);
    }

    @Rollback(false)
    @Transactional
    @Test
    void ID로_Product_조회1() {
        ProductCreationRequest.builder()
                .description("상세설명")
                .price(1000)
                .name("상품")
                .build().toEntity();
    }

    @Test
    void name() {
        ProductUpdateRequest updateRequest = ProductUpdateRequest.builder()
                .name()
                .description()
                .id()
                .price(10000)
                .build();

        productService.modify(updateRequest)

    }
}