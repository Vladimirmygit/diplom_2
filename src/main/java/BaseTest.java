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


