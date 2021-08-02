// This class is used to convert numbers (e.g., 21) to their corresponding strings (e.g., "twenty one")

package utils;

import java.math.BigInteger;
import java.util.Map;

import static java.util.Map.entry;

public class NumbersToWords {

    private static final Map<Integer, String> numLtTwenty = Map.ofEntries(
            entry(0, "zero"),
            entry(1, "one"),
            entry(2, "two"),
            entry(3, "three"),
            entry(4, "four"),
            entry(5, "five"),
            entry(6, "six"),
            entry(7, "seven"),
            entry(8, "eight"),
            entry(9, "nine"),
            entry(10, "ten"),
            entry(11, "eleven"),
            entry(12, "twelve"),
            entry(13, "thirteen"),
            entry(14, "fourteen"),
            entry(15, "fifteen"),
            entry(16, "sixteen"),
            entry(17, "seventeen"),
            entry(18, "eighteen"),
            entry(19, "nineteen")
    );
    private static final Map<Integer, String> tensPlace = Map.ofEntries(
            entry(10, "ten"),
            entry(20, "twenty"),
            entry(30, "thirty"),
            entry(40, "forty"),
            entry(50, "fifty"),
            entry(60, "sixty"),
            entry(70, "seventy"),
            entry(80, "eighty"),
            entry(90, "ninety")
    );
    private static final Map<Integer, String> hundredsPlace = Map.ofEntries(
            entry(100, "one hundred"),
            entry(200, "two hundred"),
            entry(300, "three hundred"),
            entry(400, "four hundred"),
            entry(500, "five hundred"),
            entry(600, "six hundred"),
            entry(700, "seven hundred"),
            entry(800, "eight hundred"),
            entry(900, "nine hundred")
    );
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

    public static String triadToString(int oneToThreeDigitInt) {
        if (oneToThreeDigitInt <= 19) {
            return (oneToThreeDigitInt == 0) ? "" : numLtTwenty.get(oneToThreeDigitInt);
        }

        String result = "";
        int hundredsDigit;
        if (oneToThreeDigitInt >= 100) {
            hundredsDigit = (oneToThreeDigitInt / 100);
            result = hundredsPlace.get(100 * hundredsDigit);
        } else {
            hundredsDigit = 0;
        }
        int tensAndOnes = (oneToThreeDigitInt - (100 * hundredsDigit));
        int tensDigit = (tensAndOnes >= 10) ? (tensAndOnes / 10) : 0;
        if (tensAndOnes <= 19) {
            if (tensAndOnes > 0) {
                result += String.format(" %s", numLtTwenty.get(tensAndOnes));  // Use string concatenation
            }
        } else {
            int onesDigit = (tensAndOnes - 10 * tensDigit);
            String result1 = (hundredsDigit > 0) ? " " : "";
            String result2 = String.format("%s", tensPlace.get(10 * tensDigit));
            String result3 = (onesDigit > 0) ? String.format(" %s", numLtTwenty.get(onesDigit)) : "";
            result += result1 + result2 + result3;  // Use string concatenation
        }
        return result;
    }

    private static String getConvertedTriad(int triadCount, String triad, int triadGroupNumber) {
        String groupingSeparator;
        if (triadGroupNumber > 0) {
            if ((Integer.parseInt(triad.trim()) >= 100) || (triadGroupNumber < triadCount - 1)) {
                groupingSeparator = ", ";
            } else {
                groupingSeparator = " ";
            }
        } else {
            groupingSeparator = "";
        }
        String triadAsString = triadToString(Integer.parseInt(triad.trim()));
        String triadGrouping = triadGroupings.get(triadCount - triadGroupNumber - 1);

        // Use string concatenation
        return String.format("%s%s", groupingSeparator, triadAsString) +
                String.format("%s", (triadGrouping.isEmpty() ? "" : (" " + triadGrouping)));
    }

    /*
        Converts the specified BigInteger to words, as in converting 12,586,269,025 (the Fibonacci number for n=50) to:
            "twelve billion, five hundred eighty six million, two hundred sixty nine thousand twenty five"
    */
    public String numberToWords(BigInteger number) {
        StringBuilder numberAsWords;

        if (number.equals(BigInteger.ZERO)) {
            numberAsWords = new StringBuilder("zero");
        } else {
            int stringLength = String.valueOf(number).length();
            int paddedLength = stringLength + ((3 - (stringLength % 3)) % 3);
            int triadCount = paddedLength / 3;
            String formatSpecifier = "%" + paddedLength + "d";  // Use string concatenation
            String numberAsStringPadded = String.format(formatSpecifier, number);

            numberAsWords = new StringBuilder();
            for (int triadGroupNumber = 0; triadGroupNumber < triadCount; triadGroupNumber++) {

                String triad = numberAsStringPadded.substring(3 * triadGroupNumber, 3 * triadGroupNumber + 3);

                if (!triad.equals("000")) {
                    numberAsWords.append(getConvertedTriad(triadCount, triad, triadGroupNumber));
                }
            }
        }

        return numberAsWords.toString();
    }
}