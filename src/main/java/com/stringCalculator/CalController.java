package com.stringCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalController {

    @Autowired
    Calculator calculator;

    @GetMapping("/sum")
    public int getSum(@RequestParam String numbers){
        return calculator.add(numbers);
    }

}
