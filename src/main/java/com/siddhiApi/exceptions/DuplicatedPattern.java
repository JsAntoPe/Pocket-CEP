package com.siddhiApi.exceptions;

public class DuplicatedPattern extends Exception{
    public DuplicatedPattern() {
    }

    public DuplicatedPattern(String s) {
        super(s);
    }

    public DuplicatedPattern(String s, Throwable throwable) {
        super(s, throwable);
    }
}
