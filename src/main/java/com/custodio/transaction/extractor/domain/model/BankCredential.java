package com.custodio.transaction.extractor.domain.model;

import lombok.Getter;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * Represents the bank credentials to be used to access the bank system.
 *
 * @since 1.0.0
 */
@Getter
public class BankCredential {
    private final String username;
    private final String password;

    public BankCredential(@Nonnull final String username,
                          @Nonnull final String password) {
        this.username = requireNonNull(username, "The username is mandatory to build a credential.");
        this.password = requireNonNull(password, "The password is mandatory to build a credential.");
    }
}
