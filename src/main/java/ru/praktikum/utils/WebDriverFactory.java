package ru.praktikum.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Фабрика для создания экземпляров WebDriver.
 */
public class WebDriverFactory {
    /**
     * Создает WebDriver для указанного браузера.
     * @param browser Тип браузера ("chrome" или "yandex").
     * @return Настроенный WebDriver.
     */
    public static WebDriver createDriver(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--start-maximized");

        switch (browser.toLowerCase()) {
            case "yandex":
                String yandexBinary = System.getenv("LOCALAPPDATA") +
                        "\\Yandex\\YandexBrowser\\Application\\browser.exe";
                options.setBinary(yandexBinary);
                WebDriverManager.chromedriver().browserVersion("134").setup();
                return new ChromeDriver(options);
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);
        }
    }

    /**
     * Создает WebDriver для браузера Chrome по умолчанию.
     * @return Настроенный WebDriver.
     */
    public static WebDriver createDriver() {
        return createDriver("chrome");
    }
}