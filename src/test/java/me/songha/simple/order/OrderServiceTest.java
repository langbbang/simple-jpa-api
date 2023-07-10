package me.songha.simple.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        System.out.println("set up!!");

        for (int i = 1; i <= 5; i++) {
        }
    }

    @Test
    public void orderService가_Null이_아님() {
        Assertions.assertNotNull(orderService);
    }

    @Test
    void ID로_Order_조회() {
    }
}