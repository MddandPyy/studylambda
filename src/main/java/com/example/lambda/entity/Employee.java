package com.example.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private Integer age;
    private double salary;

    public Employee() {

    }
}
