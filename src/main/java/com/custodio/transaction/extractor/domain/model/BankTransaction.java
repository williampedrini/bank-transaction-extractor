package com.custodio.transaction.extractor.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import static com.custodio.transaction.extractor.domain.model.BankTransaction.Type.valueOf;
import static java.lang.String.format;
import static java.text.NumberFormat.getInstance;
import static java.util.Objects.requireNonNull;

/**
 * Representation of a bank transaction containing the data of when it occurred, value and entity which received the amount.
 *
 * @since 1.0.0
 */
@Getter
public class BankTransaction {
    /**
     * Formatter used to perform the conversion of a certain {@link String} amount to {@link Long}.
     */
    private static final NumberFormat CURRENCY_FORMATTER = getInstance(new Locale("pt", "PT"));

    private Long amount;
    private final LocalDate createdAt;
    private final String description;
    private final Type type;

    @Builder
    public BankTransaction(@Nonnull final String amount,
                           @Nonnull final LocalDate createdAt,
                           @Nonnull final String description,
                           @Nonnull final String type) {
        try {
            requireNonNull(amount, "The amount is mandatory to build a bank transaction.");
            this.amount = CURRENCY_FORMATTER
                    .parse(amount)
                    .longValue();
        } catch (final ParseException exception) {
            final var errorMessage = format("Error while converting the information for the transaction %s", description);
            System.out.println(errorMessage);
            System.out.println(exception.getMessage());
        }
        this.createdAt = requireNonNull(createdAt, "The creation date is mandatory to build a bank transaction.");
        this.description = requireNonNull(description, "The entity name is mandatory to build a bank transaction.");

        requireNonNull(type, "The transaction type is mandatory to build a bank transaction.");
        this.type = valueOf(type.toUpperCase());
    }

    public enum Type {
        CREDIT, DEBIT;
    }
}
