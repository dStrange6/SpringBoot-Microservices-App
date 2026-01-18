package com.mayank.catalog_service.web.controllers;

import com.mayank.catalog_service.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

// slice unit test for testing controller functionality
@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT
{
    @Test
    void shouldReturnProducts()
    {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber",is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }
    @Test
    void shouldReturnProductByCode()
    {
        String productCode = "BK-1984";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", productCode)
                .then()
                .statusCode(200)
                .body("code", is("BK-1984"))
                .body("name", is("1984"))
                .body("description", is("A dystopian novel exploring surveillance, totalitarianism, and loss of freedom."))
                .body("price", is(399));
    }

}