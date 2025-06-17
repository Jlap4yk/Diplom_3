package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Класс для работы со страницей регистрации.
 */
public class RegistrationPage {
//    private static final String SITE_URL = "https://stellarburgers.nomoreparties.site/";
//    private static final String REGISTRATION_PATH = "register";

    private final WebDriver browser;
    private final WebDriverWait wait;

    private final By nameField = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");
    private final By emailField = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By submitButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private final By loginLink = By.xpath("//a[contains(text(), 'Войти')]");
    private final By passwordErrorText = By.xpath("//p[contains(text(), 'Некорректный пароль')]");

    public RegistrationPage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

//    @Step("Открытие страницы регистрации")
//    public void navigateTo() {
//        browser.get(SITE_URL + REGISTRATION_PATH);
//    }

    @Step("Ввод имени: {name}")
    public void inputName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).clear();
        browser.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void inputEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        browser.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля: {password}")
    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        browser.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void pressRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Нажатие ссылки 'Войти'")
    public void pressLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Получение текста ошибки пароля")
    public String retrievePasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorText)).getText();
    }

    @Step("Регистрация пользователя: имя {name}, email {email}")
    public void performRegistration(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        pressRegisterButton();
    }
}