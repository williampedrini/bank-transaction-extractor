package com.custodio.transaction.extractor.domain.port;

import com.custodio.transaction.extractor.domain.model.BankCredential;
import com.custodio.transaction.extractor.domain.model.BankTransaction;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Representation of a repository used to perform the search for bank transactions.
 *
 * @since 1.0.0
 */
public interface BankTransactionRepository {

    /**
     * Find all existing bank transactions by a certain user credential.
     * @param credential The credential used to login into the bank system.
     * @return The collection of all bank transactions.
     */
    @Nonnull
    Collection<BankTransaction> findAllByCredential(@Nonnull BankCredential credential);
}