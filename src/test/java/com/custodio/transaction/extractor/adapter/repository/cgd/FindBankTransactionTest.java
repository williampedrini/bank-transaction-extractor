package com.custodio.transaction.extractor.adapter.repository.cgd;

import com.custodio.transaction.extractor.domain.model.BankCredential;
import com.custodio.transaction.extractor.usecase.FindBankTransaction;
import org.junit.Test;

import static java.lang.System.getProperty;
import static org.junit.Assert.assertFalse;

@SuppressWarnings("AccessOfSystemProperties")
public class FindBankTransactionTest {
    /**
     * System property used to define the username to perform the login.
     */
    private static final String USERNAME_PROPERTY = "username";

    /**
     * System property used to define the password to perform the login.
     */
    private static final String PASSWORD_PROPERTY = "password";

    @Test
    public void when_searchForCaixaGeralDeDepositosBankTransaction_And_ThereAreExistingTransactions_Then_TransactionListShouldNotBeEmpty() {
        try(final var bankTransactionRepository = new CGDBankTransactionRepository()) {
            //given
            final var underTest = new FindBankTransaction(bankTransactionRepository);
            final var credentials = new BankCredential(getProperty(USERNAME_PROPERTY), getProperty(PASSWORD_PROPERTY));
            //when
            final var actual = underTest.findAllByCredential(credentials);
            //then
            assertFalse(actual.isEmpty());
        }
    }
}