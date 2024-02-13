package com.stringCalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Division {
    @Autowired
    Common common;
    public int division(String numbers) {
        if(numbers.equals( "1,\n")){
            throw new RuntimeException("Invalid input");
        }
        String[] num = common.splitter(numbers);
        int size=num.length;
        common.throwExceptionIfAnyNegative(num, size);
        return findDivision(num, size);
    }
    private int findDivision(String[] num, int size) {
        if(num.length<2) {
            throw new RuntimeException("Input should not be empty at least provide two inputs");
        }
        int a =common.toInt(num[0]);
        int b =common.toInt(num[1]);
        int division=a/b;
        for (int i = 2; i < size; i++) {
            a = division;
            b =common.toInt(num[i]);
            division = a / b;
        }
        return division;
    }

}
