package com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;

public class P2PTransferNotFoundException extends NoSuchElementFoundException {
    public P2PTransferNotFoundException(String message, Long id) {
        super(message);
        throwLogError(id);
    }
}
