import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class LoginPage extends BaseTest {
    static String username = TestData.generateRandomUsername();
    static String email = TestData.generateRandomEmail();
    static String password = TestData.generateRandomPassword();

    public String loginWithExistingUser() {
        UserLoginData loginData = new UserLoginData();
        loginData.setEmail(email);
        loginData.setPassword(password);
        loginData.setName(username);

        Response response = given()
                .contentType("application/json")
                .body(loginData)
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
        UserLoginData loginData = new UserLoginData();
        loginData.setEmail(email);
        loginData.setPassword("wrongpassword");

        given()
                .contentType("application/json")
                .body(loginData)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(401)
                .body("success", is(false));
    }
}
