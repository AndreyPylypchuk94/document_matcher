package com.datapath.procurementdata.documentmatcher.exception;

public class NotFoundEntityException extends RuntimeException {

    private static final String MESSAGE = "Not found entity with id %s";

    public NotFoundEntityException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
