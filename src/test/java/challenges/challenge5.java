package challenges;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertTrue;

public class challenge5 extends BaseTests {

    private final static String searchKey = "porsche";

    // TODO: Create some common code which both tests call

    @Test
    public void testPorscheMakesAndModels() {
        goToCopart();
        copartHomePage.enterSearchKey(searchKey);

        copartHomePage.setEntriesPerPageTo(100);
        copartHomePage.waitForSpinnerToComeAndGo();

        List<WebElement> models = copartHomePage.getModels();

        // Use TreeMap, rather than HashMap, since TreeMap is automatically sorted by the map's keys
        //      TreeMap is an implementation of the SortedMap interface
        Map<String, Integer> modelCounts = new TreeMap<>();
        for (WebElement model : models) {
            String modelKey = model.getText();
            Integer count = modelCounts.get(modelKey);
            modelCounts.put(modelKey, (count == null) ? 1 : count + 1);
        }

        String partTitle = String.format("\nPART 1: %d distinct %s MODELS (with the counts of their occurrences)",
                modelCounts.size(), searchKey.toUpperCase());
        System.out.println(partTitle);
        for (Map.Entry<String, Integer> entry : modelCounts.entrySet()) {
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void testPorscheMakesAndDamage() {
        goToCopart();
        copartHomePage.enterSearchKey(searchKey);

        copartHomePage.setEntriesPerPageTo(100);
        copartHomePage.waitForSpinnerToComeAndGo();

        List<WebElement> damages = copartHomePage.getDamages();

        Map<String, Integer> damageCounts = new TreeMap<>();

        for (WebElement damage : damages) {
            String damageKey = damage.getText();

            // If damageKey is not in the set of categories to keep, then count it in a "MISC" category
            Set<String> damageCategoriesToKeep = Set.of("REAR END", "FRONT END", "MINOR DENT/SCRATCHES", "UNDERCARRIAGE");
            damageKey = damageCategoriesToKeep.contains(damageKey) ? damageKey : "MISC";

            Integer count = damageCounts.get(damageKey);
            damageCounts.put(damageKey, (count == null) ? 1 : count + 1);
        }

        String partTitle = String.format("\nPART 2: %s DAMAGE categories (with the counts of their occurrences)",
                searchKey.toUpperCase());
        System.out.println(partTitle);
        for (Map.Entry<String, Integer> entry : damageCounts.entrySet()) {
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue());
        }
    }
}