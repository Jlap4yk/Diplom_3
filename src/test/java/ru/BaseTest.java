package ru;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.Constants;
import ru.praktikum.utils.WebDriverFactory;

import java.time.Duration;

/**
 * Базовый класс для тестов, обеспечивающий настройку и завершение работы WebDriver.
 */
public class BaseTest {
    protected WebDriver browser;
    protected WebDriverWait wait;

    @Before
    @DisplayName("Подготовка теста: запуск браузера и открытие сайта")
    public void initialize() {
        browser = WebDriverFactory.createDriver();
        wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        browser.get(Constants.SITE_URL);
    }

    @After
    @DisplayName("Завершение теста: закрытие браузера")
    public void cleanup() {
        if (browser != null) {
            browser.quit();
        }
    }
}