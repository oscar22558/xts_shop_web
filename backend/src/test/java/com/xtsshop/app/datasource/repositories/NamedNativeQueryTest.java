package com.xtsshop.app.datasource.repositories;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class NamedNativeQueryTest {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    @Transactional
    void test(){
        Logger logger = LoggerFactory.getLogger(NamedNativeQueryTest.class);
        logger.info("=====================test named native query=============================");
        AppUser user = userJpaRepository.findUserByUsername("ken123");
        String username = user.getUsername();
        Set<Role> roles = user.getRoles();
        logger.info(username);
        roles.forEach(role->{
            logger.info(role.getName().name());
        });
    }

}
