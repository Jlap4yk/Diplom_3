package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Класс для работы с главной страницей приложения.
 */
public class MainPage {
    private final WebDriver browser;

    private final By authButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By bunsTab = By.xpath("//span[text()='Булки']/..");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/..");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
    private final By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver browser) {
        this.browser = browser;
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void pressLoginButton() {
        browser.findElement(authButton).click();
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void pressAccountButton() {
        browser.findElement(accountButton).click();
    }

    @Step("Переход в раздел 'Булки'")
    public void selectBunsTab() {
        browser.findElement(bunsTab).click();
    }

    @Step("Переход в раздел 'Соусы'")
    public void selectSaucesTab() {
        browser.findElement(saucesTab).click();
    }

    @Step("Переход в раздел 'Начинки'")
    public void selectFillingsTab() {
        browser.findElement(fillingsTab).click();
    }

    @Step("Получение текста текущего активного раздела")
    public String getActiveTabText() {
        return browser.findElement(activeTab).getText();
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isPlaceOrderButtonVisible(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}