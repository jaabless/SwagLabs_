package com.swaglabs.base;

import com.swaglabs.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static LoginPage loginPage;

    @BeforeEach
    public void setupDriver() {
        // Setup Chrome options
        ChromeOptions options = new ChromeOptions();

        // Add arguments for Docker/Linux compatibility
        options.addArguments("--headless"); // Headless mode
        options.addArguments("--no-sandbox"); // Required in Docker
        options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issues
        options.addArguments("--remote-allow-origins=*"); // Fix for some newer ChromeDriver issues

        // Optional: mute logs
        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");

        // Set driver
        driver = new ChromeDriver(options);

        // Basic setup
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Initialize login page
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
