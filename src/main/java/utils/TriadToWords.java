/*
This TriadToWords utility class is used to convert an integer triad
[1-to-3-digit integer,
 or 1-to-3-digit portion of a larger integer (representing either the thousands, millions, billions, or ... place)]
(e.g., 629) to its corresponding strings of words (e.g., "six hundred twenty nine").
*/

package utils;

import java.util.Map;

import static java.util.Map.entry;

public class TriadToWords {

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

    // PRIVATE CONSTRUCTOR

    private TriadToWords() {
        // See "Utility classes should not have public constructors" at https://rules.sonarsource.com/java/tag/design/RSPEC-1118
        throw new IllegalStateException("This is a utility class, and so should not be instantiated.");
    }

    // PUBLIC STATIC METHODS

    public static String triadToString(int oneToThreeDigitInt) {

        if (oneToThreeDigitInt <= 19) {
            return (oneToThreeDigitInt == 0) ? "" : numLtTwenty.get(oneToThreeDigitInt);
        }

        int hundredsDigit;
        String result;
        if (oneToThreeDigitInt >= 100) {
            hundredsDigit = (oneToThreeDigitInt / 100);
            result = hundredsPlace.get(100 * hundredsDigit);
        } else {
            hundredsDigit = 0;
            result = "";
        }

        int tensAndOnes = (oneToThreeDigitInt - (100 * hundredsDigit));
        if (tensAndOnes > 19) {
            int tensDigit = tensAndOnes / 10;
            int onesDigit = (tensAndOnes - 10 * tensDigit);
            result += ((hundredsDigit > 0) ? " " : "") +
                    String.format("%s", tensPlace.get(10 * tensDigit)) +
                    ((onesDigit > 0) ? String.format(" %s", numLtTwenty.get(onesDigit)) : "");
        } else {
            result += (tensAndOnes == 0) ? "" : String.format(" %s", numLtTwenty.get(tensAndOnes));
        }

        return result;
    }
}