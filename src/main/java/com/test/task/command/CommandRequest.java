package com.test.task.command;

import java.util.Optional;

public interface CommandRequest {
    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    void deleteAttributeFromJsp(String name);

    int getParameter(int number);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<Object> retrieveFromSession(String name);

    void clearSession();

    void createSession();
}
