package com.example;

import com.example.config.DatabaseConfig;
import com.example.config.WebMvcConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class ContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        assertNotNull(context);
    }
}
