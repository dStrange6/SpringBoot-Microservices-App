package com.mayank.catalog_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CatalogServiceApplicationTests {

	@Test
	void contextLoads() {  // checks whether the context can be loaded or not
	}

}
