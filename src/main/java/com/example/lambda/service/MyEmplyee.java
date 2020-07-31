package com.example.lambda.service;

import com.example.lambda.entity.Employee;

@FunctionalInterface
public interface MyEmplyee {
    public Employee get(String name,Integer age,Integer salary);
}
