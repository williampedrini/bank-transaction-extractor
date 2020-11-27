package com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.account;

import com.custodio.transaction.extractor.adapter.repository.cgd.model.AccountTransaction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AccountBalancePage {
    /**
     * The amount lines representing the header information.
     */
    private static final long AMOUNT_OF_HEADER_LINES = 2L;

    /**
     * Pattern used to identify if a certain row is a transaction information or not.
     */
    private static final Pattern TRANSACTION_INFORMATION_PATTERN = compile("(\\d{2}-\\d{2}-\\d{4})");

    /**
     * Identifier used to find the transaction information table.
     */
    private static final String TRANSACTION_INFORMATION_TABLE_ID = "//*[@class='mgtop bordertable fltl clear']/tbody";

    private final WebDriver driver;

    public AccountBalancePage(@Nonnull final WebDriver driver) {
        this.driver = requireNonNull(driver, "The webdriver is mandatory to create the account balance page.");
        initElements(driver, this);
    }

    /**
     * Retrieves the information from the current transaction table.
     * @return The transaction information found from the transaction table.
     */
    @Nonnull
    public final Collection<AccountTransaction> getTransactionInformation() {
        return driver
                .findElement(xpath(TRANSACTION_INFORMATION_TABLE_ID))
                .findElements(tagName("tr"))
                .stream()
                .skip(AMOUNT_OF_HEADER_LINES)
                .map(row -> row.findElements(tagName("td")))
                .filter(columns -> columns
                        .stream()
                        .anyMatch(column -> TRANSACTION_INFORMATION_PATTERN.matcher(column.getText()).matches()))
                .map(AccountTransaction::new)
                .collect(toUnmodifiableList());
    }
}
