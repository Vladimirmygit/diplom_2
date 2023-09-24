import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class UpdateUserDataTests extends BaseTest {
    private String accessToken;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    UpdateUserDataPage updateUserDataPage = new UpdateUserDataPage();

    @Test
    @DisplayName("Проверка изменения данных пользователя")
    @Description("Проверка изменения данных пользователя с автооризацией")
    public void testUpdateUserDataWithAuthorization() {
        userPage.createUniqueUser();
        accessToken = loginPage.loginWithExistingUser();
        updateUserDataPage.updateUserDataWithAuthorization(accessToken);
        setAccessToken(accessToken);
    }

    @Test
    @DisplayName("Проверка изменения данных пользователя")
    @Description("Проверка изменения данных пользователя без авторизации")
    public void testUpdateUserDataWithoutAuthorization() {
        accessToken = userPage.createUniqueUser();
        updateUserDataPage.updateUserDataWithoutAuthorization();
        setAccessToken(accessToken);
    }
}
