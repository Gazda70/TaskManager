package com.example.UserTasks.model;

import com.example.UserTasks.exceptions.StatusNotValidException;

public enum Status {
    OPEN, IN_PROGRESS, DONE;
    public static Status parseStatus(String status) throws StatusNotValidException {
        try {
            return Status.valueOf(status);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new StatusNotValidException();
        }
    }
}
