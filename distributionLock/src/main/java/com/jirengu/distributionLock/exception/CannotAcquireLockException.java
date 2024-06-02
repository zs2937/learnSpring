package com.jirengu.distributionLock.exception;

public class CannotAcquireLockException extends RuntimeException {

    public CannotAcquireLockException(String message) {
        super(message);
    }

}
