import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserOrdersPage extends BaseTest {

    public static void getUserOrdersWithAuthorization(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .get("/api/orders")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("orders", hasSize(greaterThanOrEqualTo(0)));
    }

    public static void getUserOrdersWithoutAuthorization() {
        given()
                .when()
                .get("/api/orders")
                .then()
                .statusCode(401)
                .body("success", is(false));
    }
}
