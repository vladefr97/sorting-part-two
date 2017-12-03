package polis.bench;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import polis.sort.QuickSort;
import polis.sort.SortUtils;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class QuickSortBench {
    private int[] a;
    private int[] b;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        a = SortUtils.generateArray(1000);
        b = SortUtils.quickSortWorstCaseArray(1000);
    }

    @Benchmark
    public void measureQuickSortWorstCase(Blackhole bh){
        bh.consume(QuickSort.quikSort(b));
    }
    @Benchmark
    public void measureQuickSort(Blackhole bh)  {
        bh.consume(QuickSort.quikSort(a));
    }

    @Benchmark
    public void mesasureQuickSortThreeDivision(Blackhole bh){
        bh.consume(QuickSort.quickSortWithThreeParts(a));
    }
    public static void sortPrint(int[] array){
        for (int x : array) {
            System.out.print(x+ " ");
        }
        System.out.println("\n");
        array = QuickSort.quickSortWithThreeParts(array);
        for (int x : array) {
            System.out.print(x+ " ");
        }

    }


    public static void main(String[] args) throws RunnerException {


        Options opt = new OptionsBuilder()
                .include(QuickSort.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();


    }
}
