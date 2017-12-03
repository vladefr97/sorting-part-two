package polis.sort;

import polis.structures.Numerical;

public class LSDSort<T extends Numerical<?>> {
    private final int r = 10;
    private int d = 20;

    public T[] sort(T[] a){

        for(int k = 0; k < d; k++){
            int[] count = new int[r];
            for(T x : a) count[x.getDigit(k)]++;
            for(int i = 1; i < r; i++)
                count[i] += count[i - 1];

            Object[] res = new Object[a.length];
            for(int i = a.length - 1; i >= 0; i--)
                res[--count[a[i].getDigit(k)]] = a[i];

            System.arraycopy(res, 0, a, 0 ,a.length);
        }
        return a;
    }

}
