package com.custodio.transaction.extractor.adapter.repository.cgd;

import com.custodio.transaction.extractor.adapter.repository.browser.BrowserFactory;
import com.custodio.transaction.extractor.adapter.repository.cgd.model.AccountTransaction;
import com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.account.AccountBalancePage;
import com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.account.AccountMenu;
import com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.dashboard.DashBoardPage;
import com.custodio.transaction.extractor.adapter.repository.cgd.model.pageobject.login.LoginPage;
import com.custodio.transaction.extractor.domain.model.BankCredential;
import com.custodio.transaction.extractor.domain.model.BankTransaction;
import com.custodio.transaction.extractor.domain.port.BankTransactionRepository;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.util.Collection;

import static com.custodio.transaction.extractor.adapter.repository.browser.BrowserFactory.CHROME_NAME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * Repository responsible for retrieving transactions from the 'Caixa Geral de Depositos' bank system.
 *
 * @since 1.0.0
 */
class CGDBankTransactionRepository implements BankTransactionRepository, AutoCloseable {
    /**
     * The amount of time to wait as timeout for the page manipulation.
     */
    private static final long TIMEOUT_IN_SECONDS = 60L;

    private final RemoteWebDriver driver;

    CGDBankTransactionRepository() {
        driver = BrowserFactory.get(CHROME_NAME);
        driver.manage()
                .timeouts()
                .implicitlyWait(TIMEOUT_IN_SECONDS, SECONDS);
    }

    @Override
    public void close() {
        driver.quit();
    }

    @Nonnull
    @Override
    public Collection<BankTransaction> findAllByCredential(@Nonnull final BankCredential credential) {
        final var loginPage = new LoginPage(driver);
        loginPage.login(credential);

        final var dashboardPage = new DashBoardPage(driver);
        dashboardPage.closeInformationModal();

        final var accountMenu = new AccountMenu(driver);
        accountMenu.accessBalancePage();

        final var accountBalancePage = new AccountBalancePage(driver);
        return accountBalancePage
                .getTransactionInformation()
                .stream()
                .map(AccountTransaction::toBankTransaction)
                .collect(toUnmodifiableList());
    }
}