import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class UserPage extends BaseTest {
    String username = TestData.generateRandomUsername();
    String email = TestData.generateRandomEmail(username);
    String password = TestData.generateRandomPassword();


    public String createUniqueUser() {
        Response response = given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + username + "\"}")
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .extract()
                .response();

        accessToken = response.path("accessToken");
        return accessToken;

    }

        public void createAlreadyRegisteredUser() {
            given()
                    .contentType("application/json")
                    .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + username + "\"}")
                    .when()
                    .post("/api/auth/register")
                    .then()
                    .statusCode(403)
                    .body("success", is(false));
        }

    public static void createUserWithMissingField() {
        given()
                .contentType("application/json")
                .body("{\"password\": \"password\", \"name\": \"User without Email\"}")
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .body("success", is(false));
    }
}
