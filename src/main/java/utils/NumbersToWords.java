package utils;

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
            entry(5, "quadrillion)"),
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

    public static void main(String[] args) {
        System.out.println(numberToWords(999));
        System.out.println(numberToWords(1000));
        System.out.println(numberToWords(1999));
        System.out.println(numberToWords(999999));
        System.out.println(numberToWords(1000000));
    }

    public static String triadToString(int oneToThreeDigitInt) {
        String result = "";
        if (oneToThreeDigitInt <= 19) {
            if (oneToThreeDigitInt == 0) {
                result = "";
            } else { result = numLtTwenty.get(oneToThreeDigitInt); }
        } else {
            int hundredsDigit;
            if (oneToThreeDigitInt >= 100) {
                hundredsDigit = (oneToThreeDigitInt / 100);
                result = hundredsPlace.get(100 * hundredsDigit);
            } else { hundredsDigit = 0; }
            int tensAndOnes = (oneToThreeDigitInt - (100 * hundredsDigit));
            int tensDigit = (tensAndOnes >= 10) ? (tensAndOnes / 10) :  0;
            if (tensAndOnes <= 19) {
                if (tensAndOnes > 0) { result += String.format("%s", numLtTwenty.get(tensAndOnes)); }
            } else {
                int onesDigit = (tensAndOnes - 10 * tensDigit);
                String result1 = (hundredsDigit > 0) ? " " : "";
                String result2 = String.format("%s", tensPlace.get(10 * tensDigit));
                String result3 = (onesDigit > 0) ? String.format(" %s", numLtTwenty.get(onesDigit)) : "";
                result += result1 + result2 + result3;
            }
        }

        return result;
    }

    public static String numberToWords(int number) {
        StringBuilder numberAsWords;
        if (number == 0) {
            numberAsWords = new StringBuilder("zero");
        } else {
            int stringLength = String.valueOf(number).length();
            int paddedLength = stringLength + ((3 - (stringLength % 3)) % 3);
            int triadCount = paddedLength / 3;
            String formatSpecifier = "%" + paddedLength + "d";
            String numberAsStringPadded = String.format(formatSpecifier, number);

            numberAsWords = new StringBuilder();
            for (int triadGroupNumber = 0; triadGroupNumber < triadCount; triadGroupNumber++) {

                String triad = numberAsStringPadded.substring(3 * triadGroupNumber, 3 * triadGroupNumber + 3);

                if (!triad.equals("000")) {

                    // TODO: To reduce cyclomatic complexity, move much of what follows into a separate function

                    String triadAsString = triadToString(Integer.valueOf(triad.trim()));
                    String triadGrouping = triadGroupings.get(triadCount - triadGroupNumber - 1);
                    String groupingSeparator;
                    if (triadGroupNumber > 0) {
                        if ((Integer.valueOf(triad.trim()) >= 100) || (triadGroupNumber < triadCount - 1)) {
                            groupingSeparator = ", ";
                        } else { groupingSeparator = " "; }
                    } else { groupingSeparator = ""; }
                    String numberAsWords1 = String.format("%s%s", groupingSeparator, triadAsString);
                    String numberAsWords2 = String.format("%s", (triadGrouping.isEmpty() ? "" : triadGrouping));
                    numberAsWords.append(numberAsWords1).append(numberAsWords2);
                }
            }
        }

        return numberAsWords.toString();
    }
}