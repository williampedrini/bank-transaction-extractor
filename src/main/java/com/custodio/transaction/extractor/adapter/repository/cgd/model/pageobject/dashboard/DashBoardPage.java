package com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.dashboard;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;

public class DashBoardPage {
    /**
     * Script used to close the information modal which appears during the page loading.
     */
    private static final String CLOSE_INFORMATION_MODAL_SCRIPT = "closeLoadingZeroPage();";

    private final WebDriver driver;

    public DashBoardPage(final WebDriver driver) {
        this.driver = requireNonNull(driver, "The webdriver is mandatory to create the dashboard page.");
        initElements(this.driver, this);
    }

    /**
     * Close the information modal shown during the first load of the dashboard page.
     */
    public void closeInformationModal() {
        final var javascriptDriver = (JavascriptExecutor) driver;
        javascriptDriver.executeScript(CLOSE_INFORMATION_MODAL_SCRIPT);
    }
}
