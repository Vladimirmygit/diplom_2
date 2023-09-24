import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class LoginPage extends BaseTest {
    static String username = TestData.generateRandomUsername();
    static String email = TestData.generateRandomEmail(username);
    static String password = TestData.generateRandomPassword();

    public String loginWithExistingUser() {
        Response response = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email  + "\", \"password\": \"" + password  + "\", \"name\": \"" + username  + "\"}")
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .extract()
                .response();

        String accessToken = response.path("accessToken");
        return accessToken;
    }

    public static void loginWithInvalidCredentials() {
        given()
                .contentType("application/json")
                .body("{\"email\": \"" + email +"\", \"password\": \"wrongpassword\"}")
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(401)
                .body("success", is(false));
    }
}
