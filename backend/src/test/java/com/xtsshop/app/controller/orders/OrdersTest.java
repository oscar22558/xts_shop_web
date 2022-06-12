package com.xtsshop.app.controller.orders;

import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
@AutoConfigureMockMvc
public class OrdersTest extends TestCase {
    @Autowired
    public Util util;

    @TestConfiguration
    public static class TestConfig{
        @Bean
        public Util util(OrderRepository orderRepository, UserRepository userRepository, RoleRepository roleRepository, ItemRepository itemRepository, OrderWithPaymentData orderWithPaymentData, AddressRepository addressRepository) {
            return new Util(orderRepository, userRepository, roleRepository, itemRepository, orderWithPaymentData, addressRepository);
        }
        @Bean
        public OrderWithPaymentData orderWithPaymentData(ItemRepository itemRepository, UserRepository userRepository, OrderRepository orderRepository, PaymentRepository paymentRepository){
            return new OrderWithPaymentData(itemRepository, userRepository, orderRepository, paymentRepository);
        }
    }

}
