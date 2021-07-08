package challenges;

import org.testng.annotations.Test;
import utils.Fibonacci;

import java.math.BigInteger;
import java.util.List;

public class challenge4 {

    private final static int maxFibonacciSeed = 300;

    @Test
    public void testFibonacci() {
        Fibonacci fibonacci = new Fibonacci();
        List<BigInteger> expectedFibonaccis = fibonacci.getExpectedFibonaccis();
        // for (int i = 0; i <= maxFibonacciSeed; i++) {
        for (int i = 0; i <= 50; i++) {
            BigInteger calculatedfibonnaciNum = fibonacci.getFibonacciNumber(i);

            // TODO: Convert fibonnaciNum from a number to words, as in converting (for i == 50):
            //    12,586,269,025
            // to:
            //    "twelve billion, five hundred eighty six million, two hundred sixty nine thousand twenty five"
            // and then compare the resulting words against the expected values from:
            // https://raw.githubusercontent.com/RussellJQA/stg-python-webdriver-cert/master/test_challenge4_expected.json

            System.out.printf("For n=%d, the expected and calculated Fibonacci numbers are: %d, %d%n", i, expectedFibonaccis.get(i), calculatedfibonnaciNum);
        }
    }
}