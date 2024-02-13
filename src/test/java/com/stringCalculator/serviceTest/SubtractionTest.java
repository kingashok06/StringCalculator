package com.stringCalculator.serviceTest;

import com.stringCalculator.service.Subtraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubtractionTest {
    @Autowired
    Subtraction calculator;

    // for empty string it will throw exception
    @Test
    public void shouldReturnExceptionOnEmptyString() {
        try {
          calculator.subtract("");
          fail("Exception expected");
        }catch (RuntimeException e){
          assertEquals("Input should not be empty at least provide two inputs",e.getMessage());
        }
    }

    // for two strings separated by commas it will return subtraction of the two
    @Test
    public void shouldReturnSubtractionOfNumbersOnTwoStrings() {
        assertEquals(3, calculator.subtract("6,3"));
    }

    // for multiple strings separated by commas it will return subtraction of all
    @Test
    public void shouldReturnSubtractionOfAllNumbers() {
        assertEquals(6,calculator.subtract("18,6,6"));
    }

    // Allow \n as delimiter along-with comma
    @Test
    public void shouldAllowNewLineAsDelimiter() {
        assertEquals(6,calculator.subtract("18\n6,6"));
    }

    // Throw exception input is invalid: "1,\n"
    @Test
    public void shouldThrowExceptionForInvalidInput() {
        try {
            calculator.subtract("1,\n");
            fail("Exception expected");
        }catch (RuntimeException e){
            assertEquals("Invalid input",e.getMessage());
        }
    }

    // Allow custom delimiter by checking // in beginning of first line
    @Test
    public void shouldAllowCustomDelimiter() {
        assertEquals(3,calculator.subtract("//;\n5;2"));
    }

    // Custom Delimiter can be custom Regex Character
    @Test
    public void shouldAllowRegexCharAsCustomDelimiter() {
        assertEquals(3,calculator.subtract("//.\n6.3"));
    }

    // Throw exception for negative numbers
    @Test
    public void shouldThrowExceptionForNegativeNumbers() {
        try{
            calculator.subtract("1,-2,3");
            fail("Exception expected");
        }catch(RuntimeException ignored) {
        }
    }

    // Exception message should have negative number
    @Test
    public void shouldHaveNegativeNumbersInException() {
        try{
            calculator.subtract("-1,-2,3");
            fail("Exception expected");
        }catch(RuntimeException e) {
            assertEquals("negatives not allowed: -1, -2",e.getMessage());
        }
    }

}
