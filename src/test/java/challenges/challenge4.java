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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExpectedFibonacciWords;
import utils.Fibonacci;
import utils.NumbersToWords;

import java.math.BigInteger;
import java.util.List;

public class challenge4 extends BaseTests {

    @DataProvider
    public Object[][] fibonacciData() {
        return new Object[][]{{300}};
    }

    @Test(priority = 6, dataProvider = "fibonacciData")
    public void testFibonacci(int maxFibonacciSeed) {

        Fibonacci f = new Fibonacci();
        List<BigInteger> actualFibonaccis = f.getGeneratedFibonacciSequence((maxFibonacciSeed));
        List<BigInteger> expectedFibonaccis = f.getExpectedFibonaccis();
        List<String> expectedFibonaccisAsWords = ExpectedFibonacciWords.getExpectedFibonacciWords();

        SoftAssert softassert = new SoftAssert();
        for (int num = 0; num <= maxFibonacciSeed; num++) {

            BigInteger fibonacciNumber = actualFibonaccis.get(num);
            String fibonacciNumberAsWords = NumbersToWords.numberToWords(fibonacciNumber);
            System.out.printf("For num=%d, the Fibonacci number is %d - %s%n", num, fibonacciNumber, fibonacciNumberAsWords);

            BigInteger expectedFibonacci = expectedFibonaccis.get(num);
            String message1 = String.format("\n\nThe Fibonncaci number for n=%d is:\n\t%d\nrather than:\n\t%d\n", num, fibonacciNumber, expectedFibonacci);
            softassert.assertEquals(fibonacciNumber, expectedFibonacci, message1);

            String expectedFibonacciWords = expectedFibonaccisAsWords.get(num);
            String message2 = String.format("\n\nThe Fibonncaci number for n=%d (as words) is:\n\t%s\nrather than:\n\t%s\n", num, fibonacciNumberAsWords, expectedFibonacciWords);
            softassert.assertEquals(fibonacciNumberAsWords, expectedFibonacciWords, message2);
        }
        softassert.assertAll();
    }
}