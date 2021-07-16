package challenges;

import base.BaseTests;
import org.testng.annotations.Test;
import utils.Fibonacci;
import utils.NumbersToWords;

import java.math.BigInteger;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class challenge4 extends BaseTests {

    private final static int maxFibonacciSeed = 300;

    // TODO: When just this test is run in isolation, prevent Selenium from bringing up the browser.
    //      [from BaseTests:startSuite()]

    @Test
    public void testFibonacci() {
        Fibonacci f = new Fibonacci();
        List<BigInteger> expectedFibonaccis = f.getExpectedFibonaccis();
        NumbersToWords n = new NumbersToWords();
        List<BigInteger> generatedFibonacciSequence = f.getGeneratedFibonacciSequence((maxFibonacciSeed));
        for (int num = 0; num <= maxFibonacciSeed; num++) {
            BigInteger fibonacciNumber = generatedFibonacciSequence.get(num);
            System.out.printf("For num=%d, the Fibonacci number is %d - %s%n", num, fibonacciNumber, n.numberToWords(fibonacciNumber));
            assertEquals(expectedFibonaccis.get(num), fibonacciNumber, "Generated Fibonacci number doesn't match expected Fibonacci number.");
        }
    }
}