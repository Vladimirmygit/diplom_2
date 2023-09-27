import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class GetUserOrdersTests extends BaseTest {
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    GetUserOrdersPage getUserOrdersPage = new GetUserOrdersPage();

   @Test
   @DisplayName("Проверка получение заказов конкретного пользователя")
   @Description("Проверка получение заказов авторизованным пользователем")
    public void testGetUserOrdersWithAuthorization() {
       accessToken = userPage.createUniqueUser();
       loginPage.loginWithInvalidCredentials();
       setAccessToken(accessToken);
       getUserOrdersPage.getUserOrdersWithAuthorization(accessToken);
    }

    @Test
    @DisplayName("Проверка получение заказов конкретного пользователя")
    @Description("Проверка получение заказов неавторизованным пользователем")
    public void testGetUserOrdersWithoutAuthorization() {
        GetUserOrdersPage.getUserOrdersWithoutAuthorization();
    }
}
