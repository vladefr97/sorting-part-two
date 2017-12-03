package polis.sort;

import java.io.IOException;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import polis.structures.IntKeyObject;
import polis.structures.IntKeyStringValueObject;



public class CountSortTest {
    @Test
    public void test01() throws IOException {
        int n = 100;
        IntKeyStringValueObject[] array = new IntKeyStringValueObject[n];
        int[] ints = SortUtils.generateArray(n);
        String[] strings = SortUtils.generateStringArray(n);
        for(int i = 0; i < n; i++)
            array[i] = new IntKeyStringValueObject(ints[i],strings[i]);
        CountSort<IntKeyStringValueObject> countSort = new CountSort<>();
        countSort.sort(array);

        Assert.assertTrue(SortUtils.isArraySorted(array, new Comparator<IntKeyStringValueObject>() {
            @Override
            public int compare(IntKeyStringValueObject o1, IntKeyStringValueObject o2) {
                return ((IntKeyObject)o1).compareTo(o2);
            }
        }));
    }

}