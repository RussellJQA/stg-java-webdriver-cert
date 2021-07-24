package challenges;

/*
Challenge 4 (Operators and Functions):
1. Write a class that displays the Fibonacci sequence up to a certain number.
    If I want the Fibonacci number for the 9th order of the sequence, I should see 21.
    Keep your function to calculate the Fibonacci sequence separate from the test file.
2. To add additional challenge to this challenge, instead of displaying the number 21, display the string representation of "twenty one".
    This will require you to use string concatenation to print out the string.
    [String concatenation is used -- along with String format() -- in class NumbersToWords.]
*/

import base.BaseTests;
import org.testng.annotations.Test;
import utils.ExpectedFibonacciWords;
import utils.Fibonacci;
import utils.NumbersToWords;

import java.math.BigInteger;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class challenge4 extends BaseTests {

    private final static int maxFibonacciSeed = 300;

    @Test(priority = 6)
    public void testFibonacci() {
        Fibonacci f = new Fibonacci();
        List<BigInteger> expectedFibonaccis = f.getExpectedFibonaccis();
        List<String> expectedFibonacciWords = ExpectedFibonacciWords.getExpectedFibonacciWords();
        List<BigInteger> generatedFibonacciSequence = f.getGeneratedFibonacciSequence((maxFibonacciSeed));
        NumbersToWords n = new NumbersToWords();
        for (int num = 0; num <= maxFibonacciSeed; num++) {
            BigInteger fibonacciNumber = generatedFibonacciSequence.get(num);
            String fibonacciNumberAsWords = n.numberToWords(fibonacciNumber);
            System.out.printf("For num=%d, the Fibonacci number is %d - %s%n", num, fibonacciNumber, fibonacciNumberAsWords);
            assertEquals(expectedFibonaccis.get(num), fibonacciNumber, "Generated Fibonacci number doesn't match its expected value.");
            assertEquals(expectedFibonacciWords.get(num), fibonacciNumberAsWords, "Generated Fibonacci number as words doesn't match its expected value.");
        }
    }
}