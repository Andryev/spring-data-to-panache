package br.com.letscode;

import static io.restassured.RestAssured.given;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
public class CarResourceTest {

    @Test
    @Order(1)
    public void testFindAll() {
        given()
                .when().get("/car")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.hasItems(1, 2, 3, 4));
    }

    @Test
    @Order(2)
    public void testDelete() {
        given().pathParam("id", 1)
                .when().delete("/car/{id}")
                .then()
                .statusCode(204);

        given()
                .when().get("/car")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.not(CoreMatchers.hasItems(1)));
    }

    @Test
    @Order(3)
    public void testSave() {

        given().pathParam("color", "Branco").pathParam("model", "Tesla")
                .when().post("/car/color/{color}/model/{model}")
                .then()
                .statusCode(200)
                .body("color", CoreMatchers.is("Branco"),
                        "model", CoreMatchers.is("Tesla"));
    }


    @Test
    @Order(4)
    public void testFindByColor() {
        given().pathParam("color", "Azul")
                .when().get("/car/color/{color}")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(1),
                        "id", CoreMatchers.hasItems(2));
    }

    @Test
    @Order(5)
    public void testFindByModel() {
        given().pathParam("model", "Camaro")
                .when().get("/car/model/{model}")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(1),
                        "[0].model", CoreMatchers.is("Camaro"),
                        "[0].color", CoreMatchers.is("Amarelo")
                );
    }

    @Test
    @Order(6)
    public void testById() {
        given().pathParam("id", 3L)
                .when().get("/car/{id}")
                .then()
                .statusCode(200)
                .body(
                        "model", CoreMatchers.is("Mustang"),
                        "id", CoreMatchers.is(3)
                );
    }
}
