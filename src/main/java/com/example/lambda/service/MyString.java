package com.example.lambda.service;

@FunctionalInterface
public interface MyString<T> {
    public String process(T t);
}
