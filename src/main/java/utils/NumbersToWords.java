// This NumbersToWords utility class is used to convert numbers (e.g., 269,025) to their corresponding strings of words (e.g., "two hundred sixty nine thousand twenty five")

package utils;

import java.math.BigInteger;
import java.util.Map;

import static java.util.Map.entry;

public class NumbersToWords {

    // PRIVATE CONSTRUCTOR

    private static final Map<Integer, String> triadGroupings = Map.ofEntries(
            entry(0, ""),
            entry(1, "thousand"),
            entry(2, "million"),
            entry(3, "billion"),
            entry(4, "trillion"),
            entry(5, "quadrillion"),
            entry(6, "quintillion"),
            entry(7, "sextillion"),
            entry(8, "septillion"),
            entry(9, "octillion"),
            entry(10, "nonillion"),
            entry(11, "decillion"),
            entry(12, "undecillion"),
            entry(13, "duodecillion"),
            entry(14, "tredecillion"),
            entry(15, "quattuordecillion"),
            entry(16, "quindecillion"),
            entry(17, "sexdecillion"),
            entry(18, "septdecillion"),
            entry(19, "octodecillion"),
            entry(20, "novemdecillion"),
            entry(21, "vigintillion")
    );

    // PRIVATE STATIC METHODS

    private NumbersToWords() {
        // See "Utility classes should not have public constructors" at https://rules.sonarsource.com/java/tag/design/RSPEC-1118
        throw new IllegalStateException("This is a utility class, and so should not be instantiated.");
    }

    private static String getConvertedTriadAndGrouping(int triadCount, String triad, int triadGroupNumber) {

        String groupingSeparator;
        if (triadGroupNumber == 0) {
            groupingSeparator = "";
        } else if ((Integer.parseInt(triad.trim()) >= 100) || (triadGroupNumber < triadCount - 1)) {
            groupingSeparator = ", ";
        } else {
            groupingSeparator = " ";
        }

        String triadAsString = String.format("%s%s", groupingSeparator, TriadToWords.triadToString(Integer.parseInt(triad.trim())));
        String triadGrouping = triadGroupings.get(triadCount - triadGroupNumber - 1);

        return triadAsString + String.format("%s", (triadGrouping.isEmpty() ? "" : (" " + triadGrouping)));
    }

    private static String stringNumberToWordsString(String numberAsStringPadded, int triadCount) {
        StringBuilder numberAsWords = new StringBuilder();
        for (int triadGroupNumber = 0; triadGroupNumber < triadCount; triadGroupNumber++) {

            String triad = numberAsStringPadded.substring(3 * triadGroupNumber, 3 * triadGroupNumber + 3);
            if (!triad.equals("000")) {
                numberAsWords.append(getConvertedTriadAndGrouping(triadCount, triad, triadGroupNumber));
            }
        }
        return numberAsWords.toString();
    }

    // PUBLIC STATIC METHODS

    /*
        Converts the specified BigInteger to words, as in converting 12,586,269,025 (the Fibonacci number for n=50) to:
            "twelve billion, five hundred eighty six million, two hundred sixty nine thousand twenty five"
    */
    public static String numberToWords(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return "zero";
        } else {
            int stringLength = String.valueOf(number).length();

            int paddedLength = stringLength + ((3 - (stringLength % 3)) % 3);
            int triadCount = paddedLength / 3;
            String formatSpecifier = "%" + paddedLength + "d";

            String numberAsStringPadded = String.format(formatSpecifier, number);

            return stringNumberToWordsString(numberAsStringPadded, triadCount);
        }
    }

    // Not currently used
    public static String numberToWords(int number) {
        return numberToWords(BigInteger.valueOf(number));
    }

}