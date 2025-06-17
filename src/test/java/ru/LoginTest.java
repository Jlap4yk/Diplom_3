package ru;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.praktikum.*;
import ru.praktikum.utils.UserGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для проверки функционала авторизации.
 */
public class LoginTest extends BaseTest {
    private MainPage homePage;
    private RegistrationPage registrationPage;
    private LoginPage authPage;
    private PasswordRecoveryPage recoveryPage;
    private String userEmail;
    private String userPassword;
    private String userName;
    private User user;
    private UserClient userClient;

    @Before
    @Override
    public void initialize() {
        super.initialize();
        homePage = new MainPage(browser);
        authPage = new LoginPage(browser);
        recoveryPage = new PasswordRecoveryPage(browser);
        registrationPage = new RegistrationPage(browser);

        // Создание тестового пользователя через API
        userName = UserGenerator.createUsername();
        userEmail = UserGenerator.createEmail();
        userPassword = UserGenerator.createPassword(8);

        user = new User(userEmail, userPassword);
        userClient = new UserClient();
        userClient.createNewUser(new User(userName, userEmail, userPassword))
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("success", equalTo(true));
    }


//        userToken = given()
//                .header("Content-type", "application/json")
//                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
//                        userEmail, userPassword, userName))
//                .post(Constants.API_AUTH_REGISTER)
//                .then()
//                .extract()
//                .path("accessToken");
//    }


        @After
        public void cleanUp () {
            // Удаление пользователя после теста
            String accessToken = userClient.checkLoginExistingUser(user)
                    .then()
                    .extract()
                    .path("accessToken");

            if (accessToken != null) {
                userClient.deleteUser(accessToken);
            }
        }

        @Test
        @DisplayName("Авторизация через кнопку 'Войти в аккаунт' на главной странице")
        public void testLoginFromMainPage () {
            homePage.pressLoginButton();
            authPage.performLogin(userEmail, userPassword);
            wait.until(ExpectedConditions.urlToBe(Constants.SITE_URL));
            assertTrue("Кнопка 'Оформить заказ' не видна", homePage.isPlaceOrderButtonVisible(wait));
        }

        @Test
        @DisplayName("Авторизация через кнопку 'Личный кабинет'")
        public void testLoginFromAccountButton () {
            homePage.pressAccountButton();
            authPage.performLogin(userEmail, userPassword);
            wait.until(ExpectedConditions.urlToBe(Constants.SITE_URL));
            assertTrue("Кнопка 'Оформить заказ' не видна", homePage.isPlaceOrderButtonVisible(wait));
        }

        @Test
        @DisplayName("Авторизация через форму восстановления пароля")
        public void testLoginFromRecoveryForm () {
            homePage.pressLoginButton();
            authPage.pressRecoveryLink();
            recoveryPage.pressLoginLink();
            authPage.performLogin(userEmail, userPassword);
            wait.until(ExpectedConditions.urlToBe(Constants.SITE_URL));
            assertTrue("Кнопка 'Оформить заказ' не видна", homePage.isPlaceOrderButtonVisible(wait));
        }
    @Test
    @DisplayName("Авторизация через форму регистрации")
    public void testLoginFromRegistrationForm() {
        homePage.pressLoginButton();
        authPage.pressRegisterLink();
        registrationPage.pressLoginLink();
        authPage.performLogin(userEmail, userPassword);
        wait.until(ExpectedConditions.urlToBe(Constants.SITE_URL));
        assertTrue("Кнопка 'Оформить заказ' не видна", homePage.isPlaceOrderButtonVisible(wait));
    }


}


