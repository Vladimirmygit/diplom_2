import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserDataPage extends BaseTest {
    static String changeUsername = TestData.generateRandomChangeUsername();
    static String changeEmail = TestData.generateRandomChangeEmail();

    public static void updateUserDataWithAuthorization(String accessToken) {
        UpdateUserData userData = new UpdateUserData(changeEmail, changeUsername);

        given()
                .contentType("application/json")
                .header("Authorization", accessToken)
                .body(userData)
                .when()
                .patch("/api/auth/user")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(changeEmail))
                .body("user.name", equalTo(changeUsername));
    }

    public static void updateUserDataWithoutAuthorization() {
        UpdateUserData userData = new UpdateUserData(changeEmail, changeUsername);

        given()
                .contentType("application/json")
                .body(userData)
                .when()
                .patch("/api/auth/user")
                .then()
                .statusCode(401)
                .body("success", is(false));
    }
}
