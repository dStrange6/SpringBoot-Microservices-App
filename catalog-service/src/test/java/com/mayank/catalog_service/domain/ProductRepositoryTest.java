package com.mayank.catalog_service.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
// slice unit test for testing repository functionality
@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",   // to make it use postgres and not search for in memory database by default
                //"spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"  // test container url to
        }
)
@Import(TestcontainersConfiguration.class) // we can use the testcontainer configuration also instead of above tc url
@Sql("/test-data.sql")
class ProductRepositoryTest
{
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts()
    {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }
    @Test
    void shouldFindProductByCode()
    {
        ProductEntity product = productRepository.findByCode("BK-1984").orElseThrow();
        assertThat(product.getName()).isEqualTo("1984");
        assertThat(product.getDescription()).isEqualTo("A dystopian novel exploring surveillance, totalitarianism, and loss of freedom.");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal(399));
        assertThat(product.getCode()).isEqualTo("BK-1984");
    }
    @Test
    void shouldNotFindProductByInvalidCode()
    {
        var productOpt = productRepository.findByCode("INVALID-CODE");
        assertThat(productOpt).isEmpty();
    }
}