package com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.login;

import com.custodio.transaction.extractor.domain.model.BankCredential;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static java.lang.System.getProperty;
import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Represents a login page entry point used to access the account main information.
 *
 * @since 1.0.0
 */
@SuppressWarnings("AccessOfSystemProperties")
public class LoginPage {
    /**
     * System property used to retrieve the url which represents the login page.
     */
    private static final String LOGIN_PAGE_URL_PROPERTY = "loginPageUrl";

    private final LoginMenuForm loginMenuForm;
    private final LoginPageForm loginPageForm;

    public LoginPage(@Nonnull final WebDriver driver) {
        requireNonNull(driver, "The driver is mandatory to create the LoginPage.");
        initElements(driver, this);
        driver.get(requireNonNull(getProperty(LOGIN_PAGE_URL_PROPERTY)));
        loginMenuForm = new LoginMenuForm(driver);
        loginPageForm = new LoginPageForm(driver);
    }

    /**
     * Performs the login into the login page based on a provided credentials.
     *
     * @param credentials The credentials used to fill the login form.
     */
    public void login(@Nonnull final BankCredential credentials) {
        requireNonNull(credentials, "The credentials is mandatory to perform the login.");
        loginMenuForm.login(credentials);
        loginPageForm.login(credentials);
    }
}