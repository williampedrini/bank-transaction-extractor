package com.custodio.transaction.extractor.adapter.repository.cgd.model;

import com.custodio.transaction.extractor.domain.model.BankTransaction;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.List;

import static com.custodio.transaction.extractor.adapter.repository.cgd.model.AccountTransaction.Type.CREDIT;
import static com.custodio.transaction.extractor.adapter.repository.cgd.model.AccountTransaction.Type.DEBIT;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Representation of an account transaction which happened in a certain date of a certain {@link Type}.
 *
 * @since 1.0.0
 */
@Getter
public class AccountTransaction {
    /**
     * Index representing the position of the transaction creation date.
     */
    private static final int CREATION_DATE_INDEX = 0;

    /**
     * Index representing the position of the transaction credit amount.
     */
    private static final int CREDIT_AMOUNT_INDEX = 3;

    /**
     * Index representing the position of the transaction debit amount.
     */
    private static final int DEBIT_AMOUNT_INDEX = 2;

    /**
     * Index representing the position of the transaction description.
     */
    private static final int DESCRIPTION_INDEX = 1;

    private final String amount;
    private final LocalDate createdAt;
    private final String description;
    private final Type type;

    public AccountTransaction(@Nonnull final List<WebElement> columns) {
        amount = ofNullable(columns.get(CREDIT_AMOUNT_INDEX))
                .map(WebElement::getText)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> {
                    final var creditAmount = columns.get(DEBIT_AMOUNT_INDEX);
                    return creditAmount.getText();
                });
        createdAt = ofNullable(columns.get(CREATION_DATE_INDEX))
                .map(WebElement::getText)
                .filter(StringUtils::isNotBlank)
                .map(creationDate -> parse(creationDate, ofPattern("dd-MM-yyyy")))
                .orElse(null);
        description = ofNullable(columns.get(DESCRIPTION_INDEX))
                .map(WebElement::getText)
                .orElse(EMPTY);
        type = ofNullable(columns.get(CREDIT_AMOUNT_INDEX))
                .map(WebElement::getText)
                .filter(StringUtils::isNotBlank)
                .map(creditAmount -> CREDIT)
                .orElse(DEBIT);

    }

    /**
     * Converts the current {@link AccountTransaction} into a {@link BankTransaction}.
     *
     * @return The object containing the converted information.
     */
    @Nonnull
    public BankTransaction toBankTransaction() {
        return BankTransaction
                .builder()
                .amount(amount)
                .createdAt(createdAt)
                .description(description)
                .type(type.name())
                .build();
    }

    /**
     * Represents the type of transaction.
     */
    enum Type {
        CREDIT, DEBIT
    }
}
