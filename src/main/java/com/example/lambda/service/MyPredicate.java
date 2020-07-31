package com.example.lambda.service;

@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
