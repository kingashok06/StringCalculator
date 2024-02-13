package com.stringCalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StringCalculatorApplicationTests {

	@Autowired
	Calculator calculator;

	// for empty string it will return zero
	@Test
	public void shouldReturnZeroOnEmptyString() {
		assertEquals(0, calculator.add(""));
	}

	// for single string it will return same string
	@Test
	public void shouldReturnNumberOnSingleString() {
		assertEquals(1, calculator.add("1"));
	}

	// for two strings separated by commas it will return sum of the two
	@Test
	public void shouldReturnSumOfNumbersOnTwoStrings() {
		assertEquals(3, calculator.add("1,2"));
	}

	// for multiple strings separated by commas it will return sum of all
	@Test
	public void shouldReturnSumOfAllNumbers() {
		assertEquals(6,calculator.add("1,2,3"));
	}

	// Allow \n as delimiter along-with comma
	@Test
	public void shouldAllowNewLineAsDelimiter() {
		assertEquals(6,calculator.add("1\n2,3"));
	}

	// Throw exception input is invalid: "1,\n"
	@Test
	public void shouldThrowExceptionForInvalidInput() {
		try {
			calculator.add("1,\n");
			fail("Exception expected");
		}catch (RuntimeException e){
			assertEquals("Invalid input",e.getMessage());
		}
	}

	// Allow custom delimiter by checking // in beginning of first line
	@Test
	public void shouldAllowCustomDelimiter() {
		assertEquals(3,calculator.add("//;\n1;2"));
	}

	// Custom Delimiter can be custom Regex Character
	@Test
	public void shouldAllowRegexCharAsCustomDelimiter() {
		assertEquals(3,calculator.add("//.\n1.2"));
	}

	// Throw exception for negative numbers
	@Test
	public void shouldThrowExceptionForNegativeNumbers() {
		try{
			calculator.add("1,-2,3");
			fail("Exception expected");
		}catch(RuntimeException ignored) {
		}
	}

	// Exception message should have negative number
	@Test
	public void shouldHaveNegativeNumbersInException() {
		try{
			calculator.add("-1,-2,3");
			fail("Exception expected");
		}catch(RuntimeException e) {
			assertEquals("negatives not allowed: -1, -2",e.getMessage());
		}
	}

}
