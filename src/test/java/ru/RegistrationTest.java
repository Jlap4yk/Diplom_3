package ru;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.*;
import ru.praktikum.utils.UserGenerator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для проверки функционала регистрации.
 */
public class RegistrationTest extends BaseTest {
    private MainPage homePage;
    private RegistrationPage regPage;
    private LoginPage authPage;
    private UserClient userClient;
    private User user;
    private String userName;
    private String userEmail;
    private String userPassword;


    @Before
    @Override
    public void initialize() {
        super.initialize();
        homePage = new MainPage(browser);
        regPage = new RegistrationPage(browser);
        authPage = new LoginPage(browser);

        homePage.pressLoginButton();
        authPage.pressRegisterLink();

        user = new User(userEmail, userPassword);
        userClient = new UserClient();

        userName = UserGenerator.createUsername();
        userEmail = UserGenerator.createEmail();
        userPassword = UserGenerator.createPassword(8);
    }

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
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulUserRegistration() {

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