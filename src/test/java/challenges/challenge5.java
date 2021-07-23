package challenges;

import base.BaseTests;
import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class challenge5 extends BaseTests {

    private final static String searchKey = "porsche";

    @Test
    public void printPorscheModels() {
        initCopartHomePage();
        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> modelCounts = copartHomePage.getColumnValueCounts(
                copartHomePage.getElementsFromColumn("model"));

        String testTitle = String.format("\nPART 1: %d distinct %s MODELS (with the counts of their occurrences)",
                modelCounts.size(), searchKey.toUpperCase());
        copartHomePage.printColumnValueCounts(modelCounts, testTitle);
    }

    @Test
    public void printPorscheDamageCategories() {
        initCopartHomePage();
        copartHomePage.searchAndSetEntriesPerPage(searchKey, 100);
        Map<String, Integer> damageCounts = copartHomePage.getColumnValueCounts(
                copartHomePage.getElementsFromColumn("damage"));

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
        copartHomePage.printColumnValueCounts(damageCounts, testTitle);
    }
}