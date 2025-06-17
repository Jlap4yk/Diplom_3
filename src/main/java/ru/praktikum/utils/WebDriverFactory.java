package ru.praktikum.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

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
                String yandexBinary = "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe";
                File yandexFile = new File(yandexBinary);
                if (!yandexFile.exists()) {
                    throw new RuntimeException("Yandex Browser не найден по пути: " + yandexBinary);
                }
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
        String browser = System.getProperty("browser", "chrome");
        return createDriver(browser);
    }
}