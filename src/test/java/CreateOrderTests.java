import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class CreateOrderTests extends BaseTest {
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    OrderPage orderPage = new OrderPage();


    @Test
    @DisplayName("Проверка Создание заказа с авторизацией")
    @Description("Проверка Создание заказа с авторизацией")
    public void testCreateOrderWithAuthorization() {
        userPage.createUniqueUser();
        accessToken = loginPage.loginWithExistingUser();
        setAccessToken(accessToken);
        orderPage.createOrderWithAuthorizationAndIngredients(accessToken);
    }

    @Test
    @DisplayName("Проверка Создание заказа без авторизации")
    @Description("Проверка Создание заказа без авторизации")
    public void testCreateOrderWithoutAuthorization() {
        orderPage.createOrderWithoutAuthorization();
    }
    @Test
    @DisplayName("Проверка Создание заказа с ингредиентами")
    @Description("Проверка Создание заказа с ингредиентами")
    public void testCreateOrderWithIngredients() {
        userPage.createUniqueUser();
        accessToken = loginPage.loginWithExistingUser();
        setAccessToken(accessToken);
        orderPage.createOrderWithAuthorizationAndIngredients(accessToken);
    }

    @Test
    @DisplayName("Проверка Создание заказа без ингредиентов")
    @Description("Проверка Создание заказа без ингредиентов")
    public void testCreateOrderWithoutIngredients() {
        userPage.createUniqueUser();
        accessToken = loginPage.loginWithExistingUser();
        setAccessToken(accessToken);
        orderPage.createOrderWithoutIngredients(accessToken);
    }
    @Test
    @DisplayName("Проверка Создание заказа с неверным хешем ингредиентов")
    @Description("Проверка Создание заказа с неверным хешем ингредиентов")
    public void testCreateOrderWithInvalidHashIngredients() {
        userPage.createUniqueUser();
        accessToken = loginPage.loginWithExistingUser();
        setAccessToken(accessToken);
        orderPage.createOrderWithInvalidHash(accessToken);
    }

}
