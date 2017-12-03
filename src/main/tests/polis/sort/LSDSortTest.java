package polis.sort;
import java.util.Comparator;
import org.junit.Assert;
import org.junit.Test;
import polis.structures.SimpleInteger;

public class LSDSortTest {


    @Test
    public void sortInteges() throws Exception {
        LSDSort<SimpleInteger> lsdSort = new LSDSort<>();
        Integer[] a = SortUtils.generateIntegerArray(1000);
        SimpleInteger[] simpleIntegers = new SimpleInteger[1000];
        for(int i = 0; i < simpleIntegers.length; i++)
            simpleIntegers[i] = new SimpleInteger(a[i]);

        simpleIntegers = lsdSort.sort(simpleIntegers);
        Assert.assertTrue(SortUtils.isArraySorted(simpleIntegers, new Comparator<SimpleInteger>() {
            @Override
            public int compare(SimpleInteger o1, SimpleInteger o2) {
                return o1.compareTo(o2);
            }
        }));
    }


}