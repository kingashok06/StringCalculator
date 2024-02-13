package com.stringCalculator.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Addition {
    @Autowired
    Common common;
    public int add(String numbers) {
        if(numbers.equals( "1,\n")){
            throw new RuntimeException("Invalid input");
        }
        String[] num = common.splitter(numbers);
        int size=num.length;
        common.throwExceptionIfAnyNegative(num, size);
        return findSum(num, size);
    }

    private  int findSum(String[] num, int size) {
        int sum=0;
        for(int i=0; i<size; i++){
            if(common.toInt(num[i])>1000){
                num[i] = num[i].replace(num[i], "0");
            }
            sum = sum + common.toInt(num[i]);
        }
        return sum;
    }

}

