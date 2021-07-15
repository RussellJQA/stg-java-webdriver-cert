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
        // System.out.println(triadToString(123));
        System.out.println(numberToWords(1234));
    }

    public static String triadToString(int one_to_three_digit_int) {
        String result = "";
        int hundreds_digit;
        int tens_and_ones;
        int tens_digit;
        int ones_digit;

        if (one_to_three_digit_int <= 19) {
            if (one_to_three_digit_int == 0) {
                result = "";
            } else {
                result = numLtTwenty.get(one_to_three_digit_int);
            }
        } else {
            if (one_to_three_digit_int >= 100) {
                hundreds_digit = (one_to_three_digit_int / 100);
                result = hundredsPlace.get(100 * hundreds_digit);
            } else {
                hundreds_digit = 0;
            }
            tens_and_ones = (one_to_three_digit_int - (100 * hundreds_digit));

            if (tens_and_ones >= 10) {
                tens_digit = (tens_and_ones / 10);
            } else {
                tens_digit = 0;
            }

            if (tens_and_ones <= 19) {
                if (tens_and_ones > 0) {
                    result += String.format("%s", numLtTwenty.get(tens_and_ones));
                }
            } else {
                ones_digit = (tens_and_ones - 10 * tens_digit);
//                if (tens_digit == 0) {
//                    if (ones_digit != 0) {
//                        result += String.format("%s", numLtTwenty.get(ones_digit));
//                    }
//                } else {
//                    String addend1 = (hundreds_digit > 0) ? " " : "";
//                    String addend2 = String.format("%s", tensPlace.get(10 * tens_digit));
//                    String addend3 = (ones_digit > 0) ? String.format(" %s", numLtTwenty.get(ones_digit)) : "";
//                    result += addend1 + addend2 + addend3;
//                }

                String result1 = (hundreds_digit > 0) ? " " : "";
                String result2 = String.format("%s", tensPlace.get(10 * tens_digit));
                String result3 = (ones_digit > 0) ? String.format(" %s", numLtTwenty.get(ones_digit)) : "";
                result += result1 + result2 + result3;
            }
        }

        return result;
    }

    public static String numberToWords(int number) {
        StringBuilder numberAsWords;
        String numberAsString;
        int stringLength;
        int paddedLength;
        int triadCount;
        String numberAsStringPadded;
        String groupingSeparator;
        String formatSpecifier;
        String triad;
        String triadAsString;
        String triadGrouping;

        if (number == 0) {
            numberAsWords = new StringBuilder("zero");
        } else {
            numberAsString = String.valueOf(number);
            stringLength = numberAsString.length();

            paddedLength = stringLength + ((3 - (stringLength % 3)) % 3);
            triadCount = paddedLength / 3;
            formatSpecifier = "%" + paddedLength + "d";
            numberAsStringPadded = String.format(formatSpecifier, number);

            numberAsWords = new StringBuilder();
            for (int triadGroupNumber = 0; triadGroupNumber < triadCount; triadGroupNumber++) {

                triad = numberAsStringPadded.substring(3 * triadGroupNumber, 3 * triadGroupNumber + 3);

                if (!triad.equals("000")) {
                    triadAsString = triadToString(Integer.valueOf(triad.trim()));
                    triadGrouping = triadGroupings.get(triadCount - triadGroupNumber - 1);

                    if (triadGroupNumber > 0) {
                        if ((Integer.valueOf(triad) >= 100) || (triadGroupNumber < triadCount - 1)) {
                            groupingSeparator = ", ";
                        } else {
                            groupingSeparator = " ";
                        }
                    } else {
                        groupingSeparator = "";
                    }

                    String numberAsWords1 = String.format("%s%s", groupingSeparator, triadAsString);
                    String numberAsWords2 = String.format("%s", (triadGrouping.isEmpty() ? "" : triadGrouping));
                    numberAsWords.append(numberAsWords1).append(numberAsWords2);
                }
            }
        }

        return numberAsWords.toString();
    }
}