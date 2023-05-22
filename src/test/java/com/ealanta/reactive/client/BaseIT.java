package com.ealanta.reactive.client;

import com.ealanta.reactive.config.DatabaseConfig;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(properties = {"spring.main.allow-bean-definition-overriding=true","spring.docker.compose.enabled=false"})
@Import({DatabaseConfig.class})
public class BaseIT {
}
