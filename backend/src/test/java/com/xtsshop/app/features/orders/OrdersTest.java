package com.xtsshop.app.features.orders;

import com.xtsshop.app.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class OrdersTest extends TestCase {
    @Autowired
    public OrderTestHelper orderTestHelper;
}
