package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
This class has functions for dealing with Fibonacci numbers, and Fibonacci
sequences. For an explanation of Fibonacci numbers and Fibonacci sequences,
see https://en.wikipedia.org/wiki/Fibonacci_number
*/

public class Fibonacci {

    /*
    This is a list of the first 301 Fibonacci numbers from
    "The first 300 Fibonacci numbers, completely factorised"
            at http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibtable.html
            [The referenced Web page lists 301 Fibonacci numbers, those for n=0 through n=300.]
    */


    private final Map<Integer, BigInteger> cache = new HashMap<>();

    public List<BigInteger> getExpectedFibonaccis() {
        List<BigInteger> expectedFibonaccis = new ArrayList<>();
        List<String> expectedFibonacciStrings = ExpectedFibonacciStrings.getExpectedFibonacciStrings();
        for (String expectedFibonacciString : expectedFibonacciStrings) {
            expectedFibonaccis.add(new BigInteger(expectedFibonacciString));
        }
        return expectedFibonaccis;
    }

    public BigInteger getFibonacciNumber(int n) {

        if (n == 0) {
            return BigInteger.valueOf(0);
        } else if (n == 1) {
            return BigInteger.valueOf(1);
        } else if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            BigInteger fibonacciNumber = getFibonacciNumber(n - 1).add(getFibonacciNumber(n - 2));
            cache.put(n, fibonacciNumber);
            return fibonacciNumber;
        }
    }

    public List<BigInteger> getGeneratedFibonacciSequence(int max) {
        List<BigInteger> generatedFibonacciSequence = new ArrayList<>();
        for (int n = 0; n <= max; n++) {
            BigInteger fibonacciNumber = getFibonacciNumber(n);
            generatedFibonacciSequence.add(fibonacciNumber);
        }
        return generatedFibonacciSequence;
    }
}