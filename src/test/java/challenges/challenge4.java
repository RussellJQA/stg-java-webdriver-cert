package challenges;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExpectedFibonacciWords;
import utils.Fibonacci;
import utils.NumbersToWords;

import java.math.BigInteger;
import java.util.List;

/**
 * Challenge 4 (Operators and Functions):
 * 1. Write a class that displays the Fibonacci sequence up to a certain number.
 * If I want the Fibonacci number for the 9th order of the sequence, I should see 21.
 * Keep your function to calculate the Fibonacci sequence separate from the test file.
 * 2. To add additional challenge to this challenge, instead of displaying the number 21, display the string representation of "twenty one".
 * This will require you to use string concatenation to print out the string.
 * [String concatenation is used -- along with String format() -- in class NumbersToWords.]
 */
public final class challenge4 extends BaseTests {

    @DataProvider
    private static Object[][] fibonacciData() {
        return new Object[][]{{300}};
    }

    // ----------------------------------------------------------------------
    // Public instance methods
    // ----------------------------------------------------------------------

    /**
     * For n=0 to the specified maxFibonacciSeed, print Fibonacci(n), as both a number and as words
     *
     * @param maxFibonacciSeed maximum "seed" for the Fibonnaci function (e.g., 300)
     */
    @Test(priority = 6, dataProvider = "fibonacciData")
    public void testFibonacci(int maxFibonacciSeed) {

        List<BigInteger> expectedFibonaccis = Fibonacci.getExpectedFibonaccis();
        if (maxFibonacciSeed > expectedFibonaccis.size() - 1) {
            throw new IllegalArgumentException(String.format("The specified maxFibonacciSeed (%,d) is greater than the seed (%,d) of the largest expected Fibonacci number", maxFibonacciSeed, expectedFibonaccis.size() - 1));
        }
        List<String> expectedFibonaccisAsWords = ExpectedFibonacciWords.getExpectedFibonacciWords();

        Fibonacci f = new Fibonacci();
        List<BigInteger> actualFibonaccis = f.getGeneratedFibonacciSequence((maxFibonacciSeed));

        SoftAssert softassert = new SoftAssert();
        for (int num = 0; num <= maxFibonacciSeed; num++) {

            // ----------------------------------------------------------------------
            // GIVEN you get the expected Fibonacci number for the given number,
            //  both as a number, and as words
            // ----------------------------------------------------------------------

            BigInteger expectedFibonacci = expectedFibonaccis.get(num);
            String expectedFibonacciWords = expectedFibonaccisAsWords.get(num);

            // ----------------------------------------------------------------------
            // WHEN you generate the actual Fibonacci number for the same given number,
            //  both as a number, and as words
            // ----------------------------------------------------------------------

            BigInteger fibonacciNumber = actualFibonaccis.get(num);
            String fibonacciNumberAsWords = NumbersToWords.numberToWords(fibonacciNumber);
            System.out.printf("For num=%d, the Fibonacci number is %,d - %s%n", num, fibonacciNumber, fibonacciNumberAsWords);

            // ----------------------------------------------------------------------
            // THEN the actual values should match the expected values
            // ----------------------------------------------------------------------

            String message1 = String.format("\n\nThe Fibonncaci number for n=%d is:\n\t%d\nrather than:\n\t%d\n", num, fibonacciNumber, expectedFibonacci);
            softassert.assertEquals(fibonacciNumber, expectedFibonacci, message1);

            String message2 = String.format("\n\nThe Fibonncaci number for n=%d (as words) is:\n\t%s\nrather than:\n\t%s\n", num, fibonacciNumberAsWords, expectedFibonacciWords);
            softassert.assertEquals(fibonacciNumberAsWords, expectedFibonacciWords, message2);
        }
        softassert.assertAll();
    }
}