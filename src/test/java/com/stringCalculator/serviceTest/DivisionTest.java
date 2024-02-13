package com.stringCalculator.serviceTest;

import com.stringCalculator.service.Division;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DivisionTest {
    @Autowired
    Division calculator;

    // for empty string it will return Exception
    @Test
    public void shouldThrowExceptionForInvalidInputEmptyString() {
        try {
            calculator.division("");
            fail("Exception expected");
        }catch (RuntimeException e){
            assertEquals("Input should not be empty at least provide two inputs",e.getMessage());
        }
    }

    // for two strings separated by commas it will return division of the two
    @Test
    public void shouldReturnDivisionOfNumbersOnTwoStrings() {
        assertEquals(2, calculator.division("2,1"));
    }

    // for multiple strings separated by commas it will return division of all
    @Test
    public void shouldReturnDivisionOfAllNumbers() {
        assertEquals(2,calculator.division("8,2,2"));
    }

    // Allow \n as delimiter along-with comma
    @Test
    public void shouldAllowNewLineAsDelimiter() {
        assertEquals(4,calculator.division("16\n2,2"));
    }

    // Throw exception input is invalid: "1,\n"
    @Test
    public void shouldThrowExceptionForInvalidInput() {
        try {
            calculator.division("1,\n");
            fail("Exception expected");
        }catch (RuntimeException e){
            assertEquals("Invalid input",e.getMessage());
        }
    }

    // Allow custom delimiter by checking // in beginning of first line
    @Test
    public void shouldAllowCustomDelimiter() {
        assertEquals(1,calculator.division("//;\n2;2"));
    }

    // Custom Delimiter can be custom Regex Character
    @Test
    public void shouldAllowRegexCharAsCustomDelimiter() {
        assertEquals(3,calculator.division("//.\n6.2"));
    }

    // Throw exception for negative numbers
    @Test
    public void shouldThrowExceptionForNegativeNumbers() {
        try{
            calculator.division("1,-2,3");
            fail("Exception expected");
        }catch(RuntimeException ignored) {
        }
    }

    // Exception message should have negative number
    @Test
    public void shouldHaveNegativeNumbersInException() {
        try{
            calculator.division("-1,-2,3");
            fail("Exception expected");
        }catch(RuntimeException e) {
            assertEquals("negatives not allowed: -1, -2",e.getMessage());
        }
    }

}
