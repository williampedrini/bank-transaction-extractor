package com.custodio.transaction.extractor.usecase;

import com.custodio.transaction.extractor.domain.model.BankCredential;
import com.custodio.transaction.extractor.domain.model.BankTransaction;
import com.custodio.transaction.extractor.domain.port.BankTransactionRepository;

import javax.annotation.Nonnull;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class FindBankTransaction {

    private final BankTransactionRepository bankTransactionRepository;

    public FindBankTransaction(@Nonnull final BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = requireNonNull(bankTransactionRepository);
    }

    /**
     * Find all existing bank transactions by a certain user credential.
     * @param credential The credential used to login into the bank system.
     * @return The collection of all bank transactions.
     */
    @Nonnull
    public Collection<BankTransaction> findAllByCredential(@Nonnull final BankCredential credential) {
        requireNonNull(credential, "The credential is mandatory to perform the login into the bank account.");
        return bankTransactionRepository.findAllByCredential(credential);
    }
}
