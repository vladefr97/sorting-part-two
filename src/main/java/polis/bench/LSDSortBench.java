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
import polis.sort.LSDSort;
import polis.sort.SortUtils;
import polis.structures.SimpleInteger;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MICROSECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MICROSECONDS)
@Fork(1)
public class LSDSortBench {
    private int index;
    private static SimpleInteger[][] simpleIntegers;
    private static LSDSort<SimpleInteger> simpleIntegerLSDSort;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        simpleIntegerLSDSort = new LSDSort<>();

        int m = 10;
        simpleIntegers = new SimpleInteger[m][];
        for(int i = 0; i < m; i++)
        {
            Integer[] t = new Integer[m*(i + 1)];
            simpleIntegers[i] = new SimpleInteger[t.length];
            t = SortUtils.generateIntegerArray(t.length);
            for(int j = 0; j < t.length; j++){
                simpleIntegers[i][j] = new SimpleInteger(t[j]);
            }
        }



    }
    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        index = (index + 1) % 10;

    }
    @Benchmark
    public void measureLSDSort(){
        simpleIntegerLSDSort.sort(simpleIntegers[index]);

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LSDSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
