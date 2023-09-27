import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class LoginTests extends BaseTest {
    private String accessToken;
    LoginPage loginPage = new LoginPage();
    UserPage userPage = new UserPage();

    @Test
    @DisplayName("Проверка авторизации пользователя")
    @Description("Проверка авторизации пользователя с валидными данными")
    public void testLoginWithExistingUser() {
        userPage.createUniqueUser();
        accessToken = loginPage.loginWithExistingUser();
        setAccessToken(accessToken);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя")
    @Description("Проверка авторизации пользователя с невалидными данными")
    public void testLoginWithInvalidCredentials() {
        accessToken = userPage.createUniqueUser();
        loginPage.loginWithInvalidCredentials();
        setAccessToken(accessToken);
    }
}
