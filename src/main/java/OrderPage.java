import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class OrderPage extends BaseTest {

    public static void createOrderWithAuthorizationAndIngredients(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType("application/json")
                .body("{\"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\", \"61c0c5a71d1f82001bdaaa6f\"]}")
                .when()
                .post("/api/orders")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("name", equalTo("Бессмертный флюоресцентный бургер"))
                .body("order.number", notNullValue());
    }

    public static void createOrderWithoutAuthorization() {
        given()
                .contentType("application/json")
                .body("{\"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\", \"61c0c5a71d1f82001bdaaa6f\"]}")
                .when()
                .post("/api/orders")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("name", equalTo("Бессмертный флюоресцентный бургер"))
                .body("order.number", notNullValue());
    }
    public static void createOrderWithoutIngredients(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType("application/json")
                .when()
                .post("/api/orders")
                .then()
                .statusCode(400);

    }
    public static void createOrderWithInvalidHash(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType("application/json")
                .body("{\"ingredients\": [\"11a60d3b41abdacab0026a733c6\", \"609646e4dc916e00276b2870\"]}")
                .when()
                .post("/api/orders")
                .then()
                .statusCode(500);
    }
}
