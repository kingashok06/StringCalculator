package com.stringCalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Subtraction {
    @Autowired
    Common common;
    public int subtract(String numbers) {
        if(numbers.equals( "1,\n")){
            throw new RuntimeException("Invalid input");
        }
        String[] num = common.splitter(numbers);
        int size=num.length;
        common.throwExceptionIfAnyNegative(num, size);
        return findSubtraction(num, size);
    }
    private  int findSubtraction(String[] num, int size) {
        if(num.length<2) {
            throw new RuntimeException("Input should not be empty at least provide two inputs");
        }
        int a= common.toInt(num[0]);
        int b= common.toInt(num[1]);
        int subtract =a-b;
        for(int i=2; i<size; i++){
            a=subtract;
            b=common.toInt(num[i]);
            subtract =a-b;
        }
        return subtract;
    }

}
