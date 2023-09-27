

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class OrderPage extends BaseTest {



    public static void createOrderWithAuthorizationAndIngredients(String accessToken) {
        TestData testData = new TestData();
        List<String> ingredientIds = testData.getFirstTwoIngredientIds();
        String firstId = ingredientIds.get(0);
        String secondId = ingredientIds.get(1);
        given()
                .header("Authorization", accessToken)
                .contentType("application/json")
                .body("{\"ingredients\": [\"" + firstId + "\", \"" + secondId + "\"]}")
                .when()
                .post("/api/orders")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("name", equalTo("Бессмертный флюоресцентный бургер"))
                .body("order.number", notNullValue());
    }

    public static void createOrderWithoutAuthorization() {
        TestData testData = new TestData();
        List<String> ingredientIds = testData.getFirstTwoIngredientIds();
        String firstId = ingredientIds.get(0);
        String secondId = ingredientIds.get(1);
        given()
                .contentType("application/json")
                .body("{\"ingredients\": [\"" + firstId + "\", \"" + secondId + "\"]}")
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
