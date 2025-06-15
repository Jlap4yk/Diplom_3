package ru;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.*;
import ru.praktikum.utils.UserGenerator;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для проверки функционала регистрации.
 */
public class RegistrationTest extends BaseTest {
    private MainPage homePage;
    private RegistrationPage regPage;
    private LoginPage authPage;
    private String userToken;

    @Before
    @Override
    public void initialize() {
        super.initialize();
        homePage = new MainPage(browser);
        regPage = new RegistrationPage(browser);
        authPage = new LoginPage(browser);

        homePage.pressLoginButton();
        authPage.pressRegisterLink();
    }

    @After
    @Override
    public void cleanup() {
        // Удаление пользователя через API
        if (userToken != null) {
            given()
                    .header("Authorization", userToken)
                    .delete(Constants.API_USER_ENDPOINT);
        }
        super.cleanup();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulUserRegistration() {
        String userName = UserGenerator.createUsername();
        String userEmail = UserGenerator.createEmail();
        String userPassword = UserGenerator.createPassword(8);

        regPage.performRegistration(userName, userEmail, userPassword);
        assertTrue("Форма входа не отображается", authPage.isSubmitButtonVisible());
    }

    @Test
    @DisplayName("Ошибка при использовании пароля короче 6 символов")
    public void testInvalidPasswordLength() {
        String userName = UserGenerator.createUsername();
        String userEmail = UserGenerator.createEmail();
        String userPassword = UserGenerator.createPassword(5);

        regPage.performRegistration(userName, userEmail, userPassword);
        assertEquals("Неверное сообщение об ошибке пароля",
                "Некорректный пароль", regPage.retrievePasswordError());
    }
}