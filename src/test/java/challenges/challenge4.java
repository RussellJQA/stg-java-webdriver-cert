package challenges;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Map.entry;

public class challenge4 {

    private Map<Integer, Long> cache = new HashMap<>(Map.of(0, 0L, 1, 1L ));

    // Recursively generate the nth Fibonacci number
    public long get_fibonacci_number(int n) {

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        return get_fibonacci_number (n - 1) + get_fibonacci_number (n - 2);
    }

    @Test
    public void testFibonacci() {
        for (int i = 0; i <= 100; i++) {
            System.out.format("For n=%d, the fibonnaci number is: %d\n", i, get_fibonacci_number(i));
        }

    }
}
