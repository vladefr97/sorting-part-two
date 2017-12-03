package polis.bench;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


import polis.sort.QuickSortOnComparisons;
import polis.sort.SortUtils;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MICROSECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MICROSECONDS)
@Fork(1)
public class QuickSortOnComparisonsBench {
    static int[]a;
    static Integer[] array;
    static QuickSortOnComparisons<Integer> quickSortOnComparisons;
    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        a = SortUtils.generateArray(1000);
        quickSortOnComparisons = new QuickSortOnComparisons<>();
        array = new Integer[1000];
        int pos = 0;
        for(int x : a)
            array[pos++] = x;
    }
    @Benchmark
    public void measureQuickSort(Blackhole bh) throws IOException {

        quickSortOnComparisons.sort(array);
    }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QuickSortOnComparisonsBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
