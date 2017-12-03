package polis.sort;

import polis.structures.IntKeyObject;

public class CountSort<T extends IntKeyObject<?>> extends AbstractSortOnComparisons<T>{


    @Override
    public void sort(T[] array) {
        int max = findMax(array);
        int[] count = new int[max + 1];
        for(T x : array) count[x.getKey()]++;
        for(int i = 1; i <= max; i++)
            count[i] +=count[i - 1];

        Object[] res = new Object[array.length];
        for(int i = array.length - 1; i >= 0 ; i--)
            res[--count[array[i].getKey()]] = array[i];
        System.arraycopy(res,0, array,0, array.length);
    }   
    private int findMax(T[] a){
        int max = 0;
        for(T x : a)
            max = Math.max(max, x.getKey());
        return max;
    }
}
