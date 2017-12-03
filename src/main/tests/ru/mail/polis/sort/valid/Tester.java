package ru.mail.polis.sort.valid;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.rules.TestRule;

import polis.sort.BubbleSort;
import polis.sort.SortUtils;

@RunWith(value = Parameterized.class)
public class Tester {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.err.println("=== Running " + description.getMethodName());
        }
    };

    @Parameterized.Parameter
    public int[] array;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][]{
            {0},
            {0, 0, 0, 0},
            {4, 3, 2, 1},
            {0, 1, 1, 0},
            {1},
            {Integer.MAX_VALUE, 0, 0, Integer.MIN_VALUE},
            SortUtils.generateArray(1),
            SortUtils.generateArray(10),
            SortUtils.generateArray(100),
            SortUtils.generateArray(1000),
            SortUtils.generateArray(10000),
        });
    }



    @Test
    public void test01_checkBubbleSort() throws IOException {
        Assert.assertTrue(SortUtils.isArraySorted(BubbleSort.sort(array)));
    }

}
