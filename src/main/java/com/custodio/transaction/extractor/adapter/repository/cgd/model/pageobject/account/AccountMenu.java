package com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AccountMenu {
    @FindBy(linkText = "Contas à ordem")
    private WebElement mainMenu;
    @FindBy(linkText = "Saldos e movimentos")
    private WebElement balanceSubMenu;

    public AccountMenu(@Nonnull final WebDriver driver) {
        requireNonNull(driver, "The driver is mandatory to create the account menu.");
        initElements(driver, this);
    }

    /**
     * Access the page responsible for presenting the details related to the balance and transactions of an account.
     */
    public void accessBalancePage() {
        mainMenu.click();
        balanceSubMenu.click();
    }
}
