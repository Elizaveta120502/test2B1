package com.test.task.command;

public interface CommandResponse {
    boolean isRedirect();

    String getPath();
}