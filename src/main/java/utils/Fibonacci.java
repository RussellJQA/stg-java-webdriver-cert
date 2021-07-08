package utils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private final Map<Integer, BigInteger> cache = new HashMap<>();

    public BigInteger getFibonacciNumber(int n) {

        if (n == 0) {
            return new BigInteger("0");
        } else if (n == 1) {
            return new BigInteger("1");
        } else if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            BigInteger fibonacciNumber = getFibonacciNumber(n - 1).add(getFibonacciNumber(n - 2));
            cache.put(n, fibonacciNumber);
            return fibonacciNumber;
        }
    }
}
