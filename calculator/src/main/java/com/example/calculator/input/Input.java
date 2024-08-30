package com.example.calculator.input;

import org.springframework.stereotype.Component;

@Component
public class Input {
	String numbers;

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "Input [numbers=" + numbers + ", getNumbers()=" + getNumbers() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
