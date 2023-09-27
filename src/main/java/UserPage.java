import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;

public class UserPage extends BaseTest {

    public String createUniqueUser() {
        UserRegistrationData userData = new UserRegistrationData();
        userData.setEmail(TestData.generateRandomEmail());
        userData.setPassword(TestData.generateRandomPassword());
        userData.setName(TestData.generateRandomUsername());

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(userData, ObjectMapperType.JACKSON_2)
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
        UserRegistrationData userData = new UserRegistrationData();
        userData.setEmail(TestData.generateRandomEmail());
        userData.setPassword(TestData.generateRandomPassword());
        userData.setName(TestData.generateRandomUsername());

        RestAssured.given()
                .contentType("application/json")
                .body(userData, ObjectMapperType.JACKSON_2)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .body("success", is(false));
    }

    public static void createUserWithMissingField()  {
        UserRegistrationData userData = new UserRegistrationData();
        userData.setPassword("password");
        userData.setName("User without Email");

        RestAssured.given()
                .contentType("application/json")
                .body(userData, ObjectMapperType.JACKSON_2)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .body("success", is(false));
    }
}
