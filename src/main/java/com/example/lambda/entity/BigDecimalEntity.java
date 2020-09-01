package com.example.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BigDecimalEntity {
    private Long id;
    private BigDecimal num;
}
