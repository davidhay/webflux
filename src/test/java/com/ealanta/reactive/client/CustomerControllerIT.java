package com.ealanta.reactive.client;

import com.ealanta.reactive.config.DatabaseConfig;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(DatabaseConfig.class)
public class CustomerControllerIT extends BaseIT {
}
