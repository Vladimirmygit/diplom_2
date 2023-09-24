import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserDataPage extends BaseTest {
    static String changeUsername = TestData.generateRandomChangeUsername();
    static String changeEmail = TestData.generateRandomChangeEmail(changeUsername);

    public static void updateUserDataWithAuthorization(String accessToken) {
        given()
                .contentType("application/json")
                .header("Authorization", accessToken)
                .body("{\"email\": \"" + changeEmail  + "\", \"name\": \"" + changeUsername  + "\"}")
                .when()
                .patch("/api/auth/user")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo("test2223@email.ru"))
                .body("user.name", equalTo("test2223"));
    }
    public static void updateUserDataWithoutAuthorization() {
        given()
                .contentType("application/json")
                .body("{\"email\": \"" + changeEmail  + "\", \"name\": \"" + changeUsername  + "\"}")
                .when()
                .patch("/api/auth/user")
                .then()
                .statusCode(401)
                .body("success", is(false));
    }
}
