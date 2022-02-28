import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

class SimpleSortIntegerTest {

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void sortIntegerCollection(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {

        LinkedList<Integer> ls=Arrays.stream(args).collect(Collectors.toCollection(LinkedList::new));
        ArrayList<Integer> arr=Arrays.stream(args).collect(Collectors.toCollection(ArrayList::new));

        LinkedList<Integer> newSortLtWith_quickSort= (LinkedList<Integer>) EfectivSort.sortIntegerCollection(EfectivSort::quickSort,
                ls, Comparator.comparingInt(Integer::intValue),0,args.length-1,LinkedList::new);


        LinkedList<Integer> newSortLtWith_piramidSort= (LinkedList<Integer>) EfectivSort.sortIntegerCollection(EfectivSort::piramidSort,
                ls, Comparator.comparingInt(Integer::intValue),0,args.length-1,LinkedList::new);


        LinkedList<Integer> newSortLtWith_interflowSort= (LinkedList<Integer>) EfectivSort.sortIntegerCollection(EfectivSort::interflowSort,
                ls, Comparator.comparingInt(Integer::intValue),0,args.length-1,LinkedList::new);

        ArrayList<Integer> newSortArrWith_quickSort= (ArrayList<Integer>) EfectivSort.sortIntegerCollection(EfectivSort::quickSort,
                arr, Comparator.comparingInt(Integer::intValue),0,args.length-1,ArrayList::new);


        ArrayList<Integer> newSortArrWith_piramidSort= (ArrayList<Integer>) EfectivSort.sortIntegerCollection(EfectivSort::piramidSort,
                arr, Comparator.comparingInt(Integer::intValue),0,args.length-1,ArrayList::new);


        ArrayList<Integer> newSortArrWith_interflowSort= (ArrayList<Integer>) EfectivSort.sortIntegerCollection(EfectivSort::interflowSort,
                arr, Comparator.comparingInt(Integer::intValue),0,args.length-1,ArrayList::new);

        Arrays.sort(args,Comparator.comparingInt(Integer::intValue));

        Assertions.assertArrayEquals(newSortLtWith_quickSort.toArray(Integer[]::new),args);
        Assertions.assertArrayEquals(newSortLtWith_piramidSort.toArray(Integer[]::new),args);
        Assertions.assertArrayEquals(newSortLtWith_interflowSort.toArray(Integer[]::new),args);

        Assertions.assertArrayEquals(newSortArrWith_quickSort.toArray(Integer[]::new),args);
        Assertions.assertArrayEquals(newSortArrWith_piramidSort.toArray(Integer[]::new),args);
        Assertions.assertArrayEquals(newSortArrWith_interflowSort.toArray(Integer[]::new),args);

    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void quickSortIntArray(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {
        Integer[] args2= Arrays.stream(args).toArray(Integer[]::new);

        EfectivSort.quickSort(args, Comparator.comparingInt(Integer::intValue),0,args.length-1);
        Arrays.sort(args2, Comparator.comparingInt(Integer::intValue));
        Assertions.assertArrayEquals(args2,args);
    }




    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void piramidSortIntArray(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {
        Integer[] args2= Arrays.stream(args).toArray(Integer[]::new);

        EfectivSort.piramidSort(args, Comparator.comparingInt(Integer::intValue),0,args.length-1);
        Arrays.sort(args2, Comparator.comparingInt(Integer::intValue));
        Assertions.assertArrayEquals(args2,args);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testIntegerRes.csv",numLinesToSkip = 1)
    void exchangeSortIntArray(@ConvertWith(IntegerArrayConverter.class) Integer[] args) {
        Integer[] args2= Arrays.stream(args).toArray(Integer[]::new);

        EfectivSort.interflowSort(args, Comparator.comparingInt(Integer::intValue),0,args.length-1);
        Arrays.sort(args2, Comparator.comparingInt(Integer::intValue));
        Assertions.assertArrayEquals(args2,args);
    }
}