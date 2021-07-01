package challenges;

import base.BaseTests;
import com.google.common.collect.Sets;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.*;

public class challenge5 extends BaseTests {

    private final static String searchKey = "porsche";

    private Map<String, Integer> getColumnValueCounts(List<WebElement> elements) {
        // Use TreeMap (an implementation of the SortedMap interface), rather than HashMap.
        //      (TreeMap is automatically sorted in ascending order of the map's keys.)
        Map<String, Integer> ColumnValueCounts = new TreeMap<>();
        for (WebElement model : elements) {
            String modelKey = model.getText();
            Integer count = ColumnValueCounts.get(modelKey);
            ColumnValueCounts.put(modelKey, (count == null) ? 1 : count + 1);
        }
        return ColumnValueCounts;
    }

    private void printColumnValueCounts(Map<String, Integer> Counts, String testTitle) {
        System.out.println(testTitle);
        for (Map.Entry<String, Integer> entry : Counts.entrySet()) {
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void printPorscheModels() {
        initCopartHomePage();
        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> modelCounts = getColumnValueCounts(copartHomePage.getElementsFromColumn("model"));

        String testTitle = String.format("\nPART 1: %d distinct %s MODELS (with the counts of their occurrences)",
                modelCounts.size(), searchKey.toUpperCase());
        printColumnValueCounts(modelCounts, testTitle);
    }

    @Test
    public void printPorscheDamageCategories() {
        initCopartHomePage();
        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> damageCounts = getColumnValueCounts(copartHomePage.getElementsFromColumn("damage"));

        int miscCount = 0;

        // Create a set of all categories which will be merged into the "MISC" category
        // NOTE: Set.of() creates an immutable set
        Set<String> miscDamageCategories = new HashSet<>(Sets.difference(damageCounts.keySet(),
                Set.of("REAR END", "FRONT END", "MINOR DENT/SCRATCHES", "UNDERCARRIAGE")));

        // Iterate over all the miscellaneous categories
        for (String category : miscDamageCategories) {
            miscCount += damageCounts.get(category); // Merge category count into count for "MISC" category
            damageCounts.remove(category); // Remove category
        }

        damageCounts.put("MISC", miscCount);

        String testTitle = String.format("\nPART 2: %s DAMAGE categories (with the counts of their occurrences)",
                searchKey.toUpperCase());
        printColumnValueCounts(damageCounts, testTitle);
    }
}