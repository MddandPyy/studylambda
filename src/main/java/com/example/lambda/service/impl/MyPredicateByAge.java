package com.example.lambda.service.impl;

import com.example.lambda.entity.Employee;
import com.example.lambda.service.MyPredicate;

public class MyPredicateByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee e) {
        return e.getAge()>=40;
    }
}
