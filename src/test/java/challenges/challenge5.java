package challenges;

/*
Challenge 5 (If/Else/Switch):

There are 2 parts to this challenge. They are completely independent of each other, and so can be run separately or together (in either order).

- Part 1:
1. Go to the https://www.copart.com main page.
2. Do a search for “porsche” and change the  drop down for “Show Entries” from 20 to 100.
3. Count how many different models of Porsche are in the results on the first page, and return in the terminal how many of each type exists.
    Possible values can be “CAYENNE S”, “BOXSTER S”, etc.

- Part 2:
1. Create a switch statement to count the types of damages.
    Count each of these types separately:
        REAR END
        FRONT END
        MINOR DENT/SCRATCHES
        UNDERCARRIAGE
    Group any other types together into a MISC type.
2. Make sure you make your code is reusable. Use a class and methods.
*/

import base.BaseTests;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class challenge5 extends BaseTests {

    private final static String searchKey = "porsche";

    @Test(priority = 7)
    public void printPorscheModels() {

        // GIVEN the Copart homepage is displayed
        initCopartHomePage();

        /*
         * WHEN the user searches for the specified search phrase (e.g., "porsche"),
         * sets the entries per page (e.g., to 100), and get counts for each of the
         * distinct values for a specified column (e.g., "model")
         */

        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> modelCounts = copartHomePage.getWebElementValueCounts(
                copartHomePage.getElementsFromColumn("model"));

        String testTitle = String.format("\nPART 1: %d distinct %s MODELS (with the counts of their occurrences)",
                modelCounts.size(), searchKey.toUpperCase());
        copartHomePage.printWebElementValueCounts(modelCounts, testTitle);
    }

    @Test(priority = 8)
    public void printPorscheDamageCategories() {

        // GIVEN the Copart homepage is displayed
        initCopartHomePage();

        /*
         * WHEN the user searches for the specified search phrase (e.g., "porsche"),
         * sets the entries per page (e.g., to 100), and get counts for each of the
         * distinct values for a specified column (e.g., "damage")
         */
        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> damageCounts = copartHomePage.getWebElementValueCounts(
                copartHomePage.getElementsFromColumn("damage"));

        /*
         * THEN Print a sorted list of those values, with their corresponding counts
         * (lumping some miscellanous values together as "MISC")
         *
         */

        // Lump all unspecified damage categories together under the "MISC" category.
        //      A LinkedHashMap maintains insertion order, so that "MISC" appears last, preceded by the others in alphabetical order
        Map<String, Integer> lumpedDamageCounts = new LinkedHashMap<>();
        int miscCount = 0;
        for (Map.Entry<String, Integer> damageCount : damageCounts.entrySet()) {
            String key = damageCount.getKey();
            switch (key) {
                case "REAR END":
                case "FRONT END":
                case "MINOR DENT/SCRATCHES":
                case "UNDERCARRIAGE":
                    lumpedDamageCounts.put(key, damageCount.getValue());
                    break;
                default:
                    miscCount += damageCounts.get(key); // Merge category count into count for "MISC" category
            }
        }
        lumpedDamageCounts.put("MISC", miscCount);

        String testTitle = String.format("\nPART 2: %s DAMAGE categories (with the counts of their occurrences)",
                searchKey.toUpperCase());
        copartHomePage.printWebElementValueCounts(lumpedDamageCounts, testTitle);
    }
}