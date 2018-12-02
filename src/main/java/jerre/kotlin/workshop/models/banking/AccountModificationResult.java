package jerre.kotlin.workshop.models.banking;

import java.util.function.Consumer;

public class AccountModificationResult {
    private final String errorMessage;
    private final Account updatedAccount;

    private AccountModificationResult(String errorMessage, Account updatedAccount) {
        this.errorMessage = errorMessage;
        this.updatedAccount = updatedAccount;
    }

    public static AccountModificationResult success(Account account) {
        return new AccountModificationResult(null, account);
    }

    public static AccountModificationResult error(String message) {
        return new AccountModificationResult(message, null);
    }

    public void onResult(Consumer<Account> successHandler, Consumer<String> errorHandler) {
        if (updatedAccount != null) {
            successHandler.accept(updatedAccount);
        } else {
            errorHandler.accept(errorMessage);
        }
    }

    public boolean isError() {
        return errorMessage != null;
    }

}
