package com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.login;

import com.custodio.transaction.extractor.domain.model.BankCredential;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nonnull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;

class LoginPageForm {

    /**
     * Script used to close the warning modal during the login initial loading.
     */
    private static final String CLOSE_WARNING_MODAL_SCRIPT = "closeLoadingZero();";

    /**
     * Script used to fill the password field.
     */
    private static final String INSERT_PASSWORD_SCRIPT = "btClick(this, %s);";

    private final WebDriver driver;

    @FindBy(id = "login_btn_1")
    private WebElement loginButton;
    @FindBy(id = "loginForm:submit")
    private WebElement loginSubmitButton;

    LoginPageForm(@Nonnull final WebDriver driver) {
        this.driver = requireNonNull(driver, "The webdriver is mandatory to create the login page form.");
        initElements(driver, this);
    }

    /**
     * Performs the login into the login page based on a provided credentials.
     *
     * @param credentials The credentials used to fill the login form.
     */
    public void login(@Nonnull final BankCredential credentials) {
        requireNonNull(credentials, "The credentials is mandatory to perform the login.");
        loginButton.click();
        closeLoginWarningModal();
        typePassword(credentials.getPassword());
        loginSubmitButton.click();
    }

    /**
     * Close an existing warning modal if it exists. It must be done due to the fact that it blocks the screen.
     */
    private void closeLoginWarningModal() {
        final var javascriptDriver = (JavascriptExecutor) driver;
        javascriptDriver.executeScript(CLOSE_WARNING_MODAL_SCRIPT);
    }

    /**
     * Type the provided password into the password input.
     *
     * @param password The password to be typed.
     */
    private void typePassword(final String password) {
        for (final var number : password.toCharArray()) {
            final var command = format(INSERT_PASSWORD_SCRIPT, number);
            final var javascriptDriver = (JavascriptExecutor) driver;
            javascriptDriver.executeScript(command);
        }
    }
}