package polis.bench;


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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import polis.sort.CountSort;
import polis.sort.SortUtils;
import polis.structures.IntKeyStringValueObject;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MICROSECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MICROSECONDS)
@Fork(1)
public class CountSortBench {
    CountSort<IntKeyStringValueObject> countSort;
    IntKeyStringValueObject[][] array;
    int index;
    @Setup(value = Level.Trial)
    public void setUpTrial() {
        int n = 100;
        int m = 10;
        array = new IntKeyStringValueObject[m][];
        for(int j = 0; j < m ;j++) {
            array[j] = new IntKeyStringValueObject[n];
            int[] ints = SortUtils.generateArray(n);
            String[] strings = SortUtils.generateStringArray(n);

            for (int i = 0; i < n; i++)
                array[j][i] = new IntKeyStringValueObject(ints[i], strings[i]);
        }

        countSort = new CountSort<>();
        }
    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        index = (index + 1) % 10;

    }

    @Benchmark
    public void measureCountSort(){
        countSort.sort(array[index]);

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CountSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();

    }
}
