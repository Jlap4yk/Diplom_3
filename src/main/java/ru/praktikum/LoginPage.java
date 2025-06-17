package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс для работы со страницей авторизации.
 */
public class LoginPage {
    private final WebDriver browser;
    private final WebDriverWait wait;

    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By submitButton = By.xpath("//button[text()='Войти']");
    private final By registrationLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By recoveryLink = By.xpath("//a[text()='Восстановить пароль']");
    private final By errorText = By.xpath("//p[contains(@class, 'input__error')]");

    public LoginPage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    @Step("Ввод email: {email}")
    public void inputEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Ввод пароля: {password}")
    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Нажатие кнопки 'Войти'")
    public void pressLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Переход по ссылке 'Зарегистрироваться'")
    public void pressRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationLink)).click();
    }

    @Step("Переход по ссылке 'Восстановить пароль'")
    public void pressRecoveryLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoveryLink)).click();
    }

//    @Step("Получение сообщения об ошибке")
//    public String retrieveErrorMessage() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText)).getText();
//    }

    @Step("Авторизация пользователя с email: {email}")
    public void performLogin(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        pressLoginButton();
    }

    @Step("Проверка отображения кнопки 'Войти'")
    public boolean isSubmitButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}