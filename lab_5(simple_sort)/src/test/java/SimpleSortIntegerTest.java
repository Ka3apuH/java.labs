import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.Comparator;

class SimpleSortIntegerTest {
    private SimpleSort simpleSort;

    @BeforeEach
    void setUp() {simpleSort =new SimpleSort();}

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void selectionSortIntArray(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {
        Integer[] args2= Arrays.stream(args).toArray(Integer[]::new);

        simpleSort.selectionSort(args, Comparator.comparingInt(Integer::intValue),0,args.length);
        Arrays.sort(args2, Comparator.comparingInt(Integer::intValue));
        Assertions.assertArrayEquals(args2,args);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void insertSortIntArray(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {
        Integer[] args2= Arrays.stream(args).toArray(Integer[]::new);

        simpleSort.insertSort(args, Comparator.comparingInt(Integer::intValue),0,args.length);
        Arrays.sort(args2, Comparator.comparingInt(Integer::intValue));
        Assertions.assertArrayEquals(args2,args);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void exchangeSortIntArray(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {
        Integer[] args2= Arrays.stream(args).toArray(Integer[]::new);

        simpleSort.exchangeSort(args, Comparator.comparingInt(Integer::intValue),0,args.length);
        Arrays.sort(args2, Comparator.comparingInt(Integer::intValue));
        Assertions.assertArrayEquals(args2,args);
    }
}