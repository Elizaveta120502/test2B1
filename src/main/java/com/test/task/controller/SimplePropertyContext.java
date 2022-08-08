package com.test.task.controller;

public class SimplePropertyContext implements PropertyContext {
    private SimplePropertyContext() {
    }

    static SimplePropertyContext getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final SimplePropertyContext INSTANCE = new SimplePropertyContext();
    }
}
