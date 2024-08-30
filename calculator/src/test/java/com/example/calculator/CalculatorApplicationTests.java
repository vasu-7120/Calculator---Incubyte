package com.example.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.calculator.input.Input;
import com.example.calculator.service.Service;

@SpringBootTest
class CalculatorApplicationTests {
	
	@Autowired
	Service service;
	
	@Autowired
	Input input;

	@Test
    public void testEmptyString() {
		input.setNumbers("//;\n1;2");
        assertEquals(3, service.add(input));
    }

    @Test
    public void testSingleNumber() {
    	input.setNumbers("5");
    	assertEquals(5, service.add(input));
    }

    @Test
    public void testTwoNumbers() {
    	input.setNumbers("1,2");
        assertEquals(3, service.add(input));
    }

    @Test
    public void testMultipleNumbers() {
    	input.setNumbers("1,2,3");
        assertEquals(6, service.add(input));
    }

    @Test
    public void testNewLineBetweenNumbers() {
    	input.setNumbers("1\n2,3");
        assertEquals(6, service.add(input));
    }

    @Test
    public void testCustomDelimiter() {
    	input.setNumbers("//;\n1;2");
        assertEquals(3, service.add(input));
    }

    @Test
    public void testAnotherCustomDelimiter() {
    	input.setNumbers("//|\n1|2|3");
        assertEquals(6, service.add(input));
    }

    @Test
    public void testNegativeNumber() {
    	input.setNumbers("1,-2,3");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.add(input);
        });
        assertEquals("Negative numbers not allowed: -2", thrown.getMessage());
    }

    @Test
    public void testMultipleNegativeNumbers() {
    	input.setNumbers("1,-2,-3,4");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.add(input);
        });
        assertEquals("Negative numbers not allowed: -2,-3", thrown.getMessage());
    }

}
