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

import base.BaseWebDriverTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class challenge5 extends BaseWebDriverTests {

    @DataProvider
    public Object[][] searchData() {
        return new Object[][]{
                {"porsche", "model", List.of()}, // Part 1
                {"porsche", "damage", List.of("MISC", "REAR END", "FRONT END", "MINOR DENT/SCRATCHES", "UNDERCARRIAGE")} // Part 2
        };
    }

    // The following test incorporates both Parts 1 and 2

    /**
     * Search copart.com for specified search key, then print distinct values of specified search results column
     *
     * @param searchKey     search phrase (e.g., "porsche") to enter into Copart.com's main search box
     * @param columnName    lowercase name of the column (in Copart.com's search results table)
     *                      whose distinct values this test should print out (with counts of their occurrences)
     *                      (e.g., "model" or "damage")
     * @param columnLumping Information on how to "lump" "miscellaneous" values for that column:
     *                      columnLumping[0]: A named category (such as "MISC") to lump "miscellaneous" values into
     *                      columnLumping[1:end] Those distinct values which won't be lumped into "MISC"
     *                      <p>
     *                      For example, columnLumping=["MISC", "FRONT END", "REAR END", "MINOR DENT/SCRATCHES", "UNDERCARRIAGE"]
     *                      will lump all values other than "FRONT END", "REAR END", "MINOR DENT/SCRATCHES", and "UNDERCARRIAGE"
     *                      together as "MISC"
     *                      <p>
     *                      If column_lumping.size() == 0, then no lumping will occur. Instead, all distinct values will be reported.
     */
    @Test(priority = 7, dataProvider = "searchData")
    public void testSearchThenPrintColumnData(String searchKey, String columnName, List<String> columnLumping) {

        // GIVEN the Copart homepage is displayed
        initCopartHomePage();

        /*
         * WHEN the user searches for the specified search phrase (e.g., "porsche"),
         * sets the entries per page (e.g., to 100), and get counts for each of the
         * distinct values for a specified column (e.g., "model" or "damage")
         */

        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> columnValueCounts = copartHomePage.getWebElementValueCounts(
                copartHomePage.getElementsFromColumn(columnName));

        // THEN Print a sorted list of those values, with their corresponding counts

        String testTitle = String.format("\n%s %s categories (with the counts of their occurrences)",
                searchKey.toUpperCase(), columnName.toUpperCase());
        if (columnLumping.size() < 2) {
            copartHomePage.printWebElementValueCounts(columnValueCounts, testTitle);
        } else {
            // Lump some miscellanous values together under some "miscellaneouse" category (e.g., "MISC")
            Map<String, Integer> lumpedColumnValueCounts = copartHomePage.getLumpedColumnValueCounts(columnValueCounts,
                    columnLumping);

            copartHomePage.printWebElementValueCounts(lumpedColumnValueCounts, testTitle);
        }
    }

    // The following version of Part 2 is deprecated, but has been left here to demonstrate the use of a switch statement.

    @DataProvider
    public Object[][] searchDataSwitch() {
        return new Object[][]{{"porsche", "damage"}};
    }

    @Test(priority = 8, dataProvider = "searchDataSwitch")
    public void testSearchThenPrintColumnDataSwitch(String searchKey, String columnName) {

        // GIVEN the Copart homepage is displayed
        initCopartHomePage();

        /*
         * WHEN the user searches for the specified search phrase (e.g., "porsche"),
         * sets the entries per page (e.g., to 100), and get counts for each of the
         * distinct values for a specified column (e.g., "damage")
         */
        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> columnValueCounts = copartHomePage.getWebElementValueCounts(
                copartHomePage.getElementsFromColumn(columnName));

        /*
         * THEN Print a sorted list of those values, with their corresponding counts
         * (lumping some miscellanous values together as "MISC")
         *
         */

        // Lump all unspecified damage categories together under the "MISC" category.
        //      A LinkedHashMap maintains insertion order, so that "MISC" appears last, preceded by the others in alphabetical order
        Map<String, Integer> lumpedColumnValueCounts = new LinkedHashMap<>();
        int miscCount = 0;
        for (Map.Entry<String, Integer> valueCount : columnValueCounts.entrySet()) {
            String key = valueCount.getKey();
            switch (key) {
                case "REAR END":
                case "FRONT END":
                case "MINOR DENT/SCRATCHES":
                case "UNDERCARRIAGE":
                    lumpedColumnValueCounts.put(key, valueCount.getValue());
                    break;
                default:
                    miscCount += columnValueCounts.get(key); // Merge category count into count for "MISC" category
            }
        }
        lumpedColumnValueCounts.put("MISC", miscCount);

        String testTitle = String.format("\nPART 2 (using switch implementation): %s %s categories (with the counts of their occurrences)",
                searchKey.toUpperCase(), columnName.toUpperCase());
        copartHomePage.printWebElementValueCounts(lumpedColumnValueCounts, testTitle);
    }
}