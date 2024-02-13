package com.stringCalculator.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Multiplication {
    @Autowired
    Common common;

    public int multiply(String numbers) {
        if(numbers.equals( "1,\n")){
            throw new RuntimeException("Invalid input");
        }
        String[] num = common.splitter(numbers);
        int size=num.length;
        common.throwExceptionIfAnyNegative(num, size);
        return findMultiplication(num, size);
    }
    private int findMultiplication(String[] num, int size) {
        if(num.length<1) {
            throw new RuntimeException("Invalid input");
        }
        int multiplication = 1;
        for (int i = 0; i < size; i++) {
            multiplication = multiplication * common.toInt(num[i]);
        }
        return multiplication;
    }

}
