import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class CreateUserTests extends BaseTest {
    UserPage userPage = new UserPage();
    private String accessToken;

    @Test
    @DisplayName("Проверка создания пользователя")
    @Description("Проверка создания уникального пользователя")
    public void testCreateUniqueUser() {
        accessToken = userPage.createUniqueUser();
        setAccessToken(accessToken);
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    @Description("Проверка создания пользователя, который уже зарегистрирован")
    public void testCreateExistingUser() {
        accessToken = userPage.createUniqueUser();
        setAccessToken(accessToken);
        userPage.createAlreadyRegisteredUser();
    }
    @Test
    @DisplayName("Проверка создания пользователя")
    @Description("Проверка создания с одним из незаполненных обязательных полей")
    public void testCreateUserWithMissingField() {
        userPage.createUserWithMissingField();

    }
}
