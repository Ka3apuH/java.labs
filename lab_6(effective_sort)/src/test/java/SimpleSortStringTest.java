import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

class SimpleSortStringTest {

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void sortStringCollection(@ConvertWith(StringArrayConverter.class) String[] args) {

        LinkedList<String> ls=Arrays.stream(args).collect(Collectors.toCollection(LinkedList::new));
        ArrayList<String> arr=Arrays.stream(args).collect(Collectors.toCollection(ArrayList::new));

        LinkedList<String> newSortLtWith_quickSort= (LinkedList<String>) EfectivSort.sortStringCollection(EfectivSort::quickSort,
                ls, Comparator.comparingInt(String::length),0,args.length-1,LinkedList::new);


        LinkedList<String> newSortLtWith_piramidSort= (LinkedList<String>) EfectivSort.sortStringCollection(EfectivSort::piramidSort,
                ls, Comparator.comparingInt(String::length),0,args.length-1,LinkedList::new);


        LinkedList<String> newSortLtWith_interflowSort= (LinkedList<String>) EfectivSort.sortStringCollection(EfectivSort::interflowSort,
                ls, Comparator.comparingInt(String::length),0,args.length-1,LinkedList::new);

        ArrayList<String> newSortArrWith_quickSort= (ArrayList<String>) EfectivSort.sortStringCollection(EfectivSort::quickSort,
                arr, Comparator.comparingInt(String::length),0,args.length-1,ArrayList::new);


        ArrayList<String> newSortArrWith_piramidSort= (ArrayList<String>) EfectivSort.sortStringCollection(EfectivSort::piramidSort,
                arr, Comparator.comparingInt(String::length),0,args.length-1,ArrayList::new);


        ArrayList<String> newSortArrWith_interflowSort= (ArrayList<String>) EfectivSort.sortStringCollection(EfectivSort::interflowSort,
                arr, Comparator.comparingInt(String::length),0,args.length-1,ArrayList::new);

        Arrays.sort(args,Comparator.comparingInt(String::length));

        Assertions.assertArrayEquals(newSortLtWith_quickSort.toArray(String[]::new),args);
        Assertions.assertArrayEquals(newSortLtWith_piramidSort.toArray(String[]::new),args);
        Assertions.assertArrayEquals(newSortLtWith_interflowSort.toArray(String[]::new),args);

        Assertions.assertArrayEquals(newSortArrWith_quickSort.toArray(String[]::new),args);
        Assertions.assertArrayEquals(newSortArrWith_piramidSort.toArray(String[]::new),args);
        Assertions.assertArrayEquals(newSortArrWith_interflowSort.toArray(String[]::new),args);

    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void selectionSortStringArray(@ConvertWith(StringArrayConverter.class) String[] args) {
        String[] args2= Arrays.stream(args).toArray(String[]::new);

        EfectivSort.quickSort(args, Comparator.comparingInt(String::length),0,args.length-1);
        Arrays.sort(args2, Comparator.comparingInt(String::length));
        Assertions.assertArrayEquals(args2,args);

        LinkedList<String> ls=Arrays.stream(args).collect(Collectors.toCollection(LinkedList::new));

        LinkedList<String> newSortLt= (LinkedList<String>) EfectivSort.sortStringCollection(EfectivSort::quickSort,ls, Comparator.comparingInt(String::length),0,args.length-1,LinkedList::new);
        Assertions.assertArrayEquals(newSortLt.toArray(String[]::new),args);

    }



    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void insertSortStringArray(@ConvertWith(StringArrayConverter.class) String[] args) {
        String[] args2= Arrays.stream(args).toArray(String[]::new);

        EfectivSort.piramidSort(args, Comparator.comparingInt(String::length),0,args.length-1);
        Arrays.sort(args2, Comparator.comparingInt(String::length));
        Assertions.assertArrayEquals(args2,args);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testSrtingRes.csv",numLinesToSkip = 1)
    void exchangeSortStringArray(@ConvertWith(StringArrayConverter.class) String[] args) {
        String[] args2= Arrays.stream(args).toArray(String[]::new);

        EfectivSort.interflowSort(args, Comparator.comparingInt(String::length),0,args.length-1);
        Arrays.sort(args2, Comparator.comparingInt(String::length));
        Assertions.assertArrayEquals(args2,args);
    }

}