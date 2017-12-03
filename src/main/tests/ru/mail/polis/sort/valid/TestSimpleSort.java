package ru.mail.polis.sort.valid;

import java.io.IOException;
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

import polis.sort.SimpleSortOnComparisons;
import polis.sort.SortUtils;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2017.
 */
@RunWith(value = Parameterized.class)
public class TestSimpleSort {

    private static SimpleSortOnComparisons<Integer> simpleSort;
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

    @BeforeClass //Перед всеми запусками тестов - should be static
    public static void init() {
        simpleSort = new SimpleSortOnComparisons<>();
        array = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    }

    @Before //Перед каждым запуском теста
    public void setComparator() {
        simpleSort.setComparator(comparator);
    }

    @Test
    public void test01() throws IOException {
        System.out.println("Before = " + Arrays.toString(array));
        simpleSort.sort(array);
        System.out.println("After = " + Arrays.toString(array));
        Assert.assertTrue(SortUtils.isArraySorted(array, comparator));
    }
}
