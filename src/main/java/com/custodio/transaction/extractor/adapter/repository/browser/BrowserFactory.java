package com.custodio.transaction.extractor.adapter.repository.browser;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static java.util.Objects.requireNonNull;

/**
 * Class used to build a certain {@link WebDriver}.
 *
 * @since 1.0.0
 */
public final class BrowserFactory {
    /**
     * Alias used to identify the chrome browser.
     */
    public static final String CHROME_NAME = "chrome";

    /**
     * Retrieves a {@link WebDriver} for a specific browser name.
     * @param browserName The browser name used to retrieve the web driver.
     * @return The found web driver. If none was found, then the default will be {@link DriverManagerType#FIREFOX}.
     */
    @Nonnull
    public static RemoteWebDriver get(@Nonnull final String browserName) {
        requireNonNull(browserName, "The browser name is mandatory to perform the driver creation.");
        if (CHROME_NAME.equalsIgnoreCase(browserName)) {
            WebDriverManager
                    .getInstance(CHROME)
                    .setup();
            return new ChromeDriver();
        }
        WebDriverManager
                .getInstance(FIREFOX)
                .setup();
        return new FirefoxDriver();
    }
}
