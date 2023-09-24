import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class BaseTest {

    protected String accessToken;

    protected void setAccessToken(String token) {
        this.accessToken = token;
    }

    protected String createdUserId;
    protected String createdOrderId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            given()
                    .contentType("application/json")
                    .header("Authorization", accessToken)
                    .when()
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202)
                    .body("success", is(true));
        }
    }
}
/*
        if (createdUserId != null) {
            given()
                    .header("Authorization", "Bearer " + getAccessToken())
                    .when()
                    .delete("/api/auth/user/" + createdUserId)
                    .then()
                    .statusCode(200);
        }

        if (createdOrderId != null) {
            given()
                    .header("Authorization", "Bearer " + getAccessToken())
                    .when()
                    .delete("/api/orders/" + createdOrderId)
                    .then()
                    .statusCode(200);
        }
    }

        protected String getAccessToken () {
            String accessToken = given()
                    .contentType("application/json")
                    .body("{\"email\": \"existing@example.com\", \"password\": \"password\"}")
                    .when()
                    .post("/api/auth/login")
                    .then()
                    .extract().path("accessToken");

            return accessToken;
      */

