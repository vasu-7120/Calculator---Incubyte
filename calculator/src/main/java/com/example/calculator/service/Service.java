package com.example.calculator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.calculator.input.Input;

@Component
public class Service {
	public int add(Input input) {
		String numbers = input.getNumbers();
		if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        String numberString = numbers;

        if (numbers.startsWith("//")) {
        	int newlineIndex = findStartIndex(numbers);
        
            delimiter = numbers.substring(2, newlineIndex);
            numberString = numbers.substring(newlineIndex + 1);
        }

        numberString = numberString.replaceAll("\n", ",").
        					replaceAll(Pattern.quote(delimiter), ",");

        String[] numberArray = numberString.split(",");

        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;

        for (String numStr : numberArray) {
            numStr = numStr.trim();
            
            if (numStr.isEmpty()) continue;

            try {
                int num = Integer.parseInt(numStr);
                if (num < 0) {
                    negativeNumbers.add(num);
                } else {
                    sum += num;
                }
            } 
            catch (NumberFormatException e) {
            	
            }
        }

        if (!negativeNumbers.isEmpty()) {
            String negativeNumbersString = String.join(",", negativeNumbers.stream().
					            		map(String::valueOf).
					            		toArray(String[]::new));
            
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbersString);
        }

        return sum;
	}
	
	public int findStartIndex(String numbers) {
		int newlineIndex = numbers.indexOf('\n');
        if (newlineIndex == -1) {
            throw new IllegalArgumentException("Invalid input format");
        }
		return newlineIndex;
	}
}
