import com.github.javafaker.Faker;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestData {
    private static Faker faker = new Faker();
    public static String generateRandomUsername() {
        String name = faker.name().firstName();
        return "nametest5";
        //return name;
    }

    public static String generateRandomEmail() {
        String email = faker.name().firstName() + "@mail.ru";
        return "nametest5@mail.ru";
        //return email;
    }

    public static String generateRandomPassword() {
        String pass = faker.name().firstName() + "pass";
        return "pass123424";
        //return pass;
    }
    public static String generateRandomChangeUsername() {
        String changeName = faker.name().firstName();
        return "nametest555";
        //return changeName;
    }

    public static String generateRandomChangeEmail() {
        String changeEmail = faker.name().firstName() + "@mail.ru";
        return "nametest52@email.ru";
        //return changeEmail;
    }
    public List<String> getFirstTwoIngredientIds() {
        List<String> ingredientIds = given()
                .get("/api/ingredients")
                .then()
                .statusCode(200)
                .extract()
                .path("data._id");

        if (ingredientIds.size() >= 2) {
            String firstId = ingredientIds.get(0);
            String secondId = ingredientIds.get(1);
            return List.of(firstId, secondId);
        }
        return List.of(); // Return an empty list if there are fewer than 2 ingredients
    }
}
