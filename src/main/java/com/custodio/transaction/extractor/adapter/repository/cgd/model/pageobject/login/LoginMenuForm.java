package com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.login;

import com.custodio.transaction.extractor.domain.model.BankCredential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;

class LoginMenuForm {
    @FindBy(css = ".direct-link.desktop")
    private WebElement menu;
    @FindBy(id = "input_cx1")
    private WebElement userNameInput;

    LoginMenuForm(@Nonnull final WebDriver driver) {
        requireNonNull(driver, "The driver is mandatory to create the login menu.");
        initElements(driver, this);
    }

    /**
     * Performs the login into the login page based on a provided credentials.
     *
     * @param credentials The credentials used to fill the login form.
     */
    public void login(final BankCredential credentials) {
        menu.click();
        userNameInput.sendKeys(credentials.getUsername());
    }
}
