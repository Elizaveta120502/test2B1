package com.test.task.controller;

public interface PropertyContext {

    static PropertyContext instance() {
        return SimplePropertyContext.getInstance();
    }
}
