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
    public OrderTestHelper orderTestHelper;
}
