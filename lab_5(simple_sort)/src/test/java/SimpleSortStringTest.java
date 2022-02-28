import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.Comparator;

class SimpleSortStringTest {
    private SimpleSort simpleSort;

    @BeforeEach
    void setUp() {simpleSort =new SimpleSort();}

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void selectionSortStringArray(@ConvertWith(StringArrayConverter.class) String[] args) {
        String[] args2= Arrays.stream(args).toArray(String[]::new);

        simpleSort.selectionSort(args, Comparator.comparingInt(String::length),0,args.length);
        Arrays.sort(args2, Comparator.comparingInt(String::length));
        Assertions.assertArrayEquals(args2,args);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void insertSortStringArray(@ConvertWith(StringArrayConverter.class) String[] args) {
        String[] args2= Arrays.stream(args).toArray(String[]::new);

        simpleSort.insertSort(args, Comparator.comparingInt(String::length),0,args.length);
        Arrays.sort(args2, Comparator.comparingInt(String::length));
        Assertions.assertArrayEquals(args2,args);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void exchangeSortStringArray(@ConvertWith(StringArrayConverter.class) String[] args) {
        String[] args2= Arrays.stream(args).toArray(String[]::new);

        simpleSort.exchangeSort(args, Comparator.comparingInt(String::length),0,args.length);
        Arrays.sort(args2, Comparator.comparingInt(String::length));
        Assertions.assertArrayEquals(args2,args);
    }

}