package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Класс для работы со страницей восстановления пароля.
 */
public class PasswordRecoveryPage {
    private final WebDriver browser;
    private final By authLink = By.xpath("//a[text()='Войти']");

    public PasswordRecoveryPage(WebDriver browser) {
        this.browser = browser;
    }

    @Step("Нажатие на ссылку 'Войти' на странице восстановления")
    public void pressLoginLink() {
        browser.findElement(authLink).click();
    }
}