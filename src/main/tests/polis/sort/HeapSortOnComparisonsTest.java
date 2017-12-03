package polis.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
@RunWith(value = Parameterized.class)
public class HeapSortOnComparisonsTest {
    private static HeapSortOnComparisons<Integer> heapSortOnComparisons;
    private static Integer[] array;
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.out.println("=== Running " + description.getMethodName());
        }
    };
    @Parameterized.Parameter
    public Comparator<Integer> comparator;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<Comparator<Integer>> data() {
        return Arrays.asList(
                Comparator.comparingInt(k -> k),
                Comparator.reverseOrder(),
                Comparator.naturalOrder(),
                Comparator.comparingInt(k -> k % 2),
                (k1, k2) -> 0
        );
    }
    @BeforeClass
    public static void init() {
        heapSortOnComparisons = new HeapSortOnComparisons<>();
        array = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    }
    @Before
    public void setComparator() {
        heapSortOnComparisons.setComparator(comparator);
    }
    @Test
    public void heapSortOnComparisons() throws Exception {
        System.out.println("Before = " + Arrays.toString(array));
        heapSortOnComparisons.sort(array);
        System.out.println("After = " + Arrays.toString(array));
        Assert.assertTrue(SortUtils.isArraySorted(array, comparator));
    }

}