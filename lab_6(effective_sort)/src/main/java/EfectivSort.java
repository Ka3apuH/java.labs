import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EfectivSort {

    //сортировка любой коллекции выбранным методом(почему не generic..... не получилось) проверил только для ArrayList и LinkedList
    public static  Collection<String> sortStringCollection(SortAlgoritm<String> sortAlgoritm/*функциональный интерфейс*/,
                                                           Collection<String> collection/*любая коллекция сторок */,
                                                           Comparator<? super String> com/*компотатор сравнеия*/,
                                                           int start, int end/*начальный элемент и конечный*/,
                                                           Supplier<Collection<String>> collectionSupplier/*сборщик обратнов коллекцию*/
    ){
        String[] sortedMass=  collection.toArray(String[]::new);
        sortAlgoritm.Sort(sortedMass,com,start,end);
        return Arrays.stream(sortedMass).collect(Collectors.toCollection(collectionSupplier));
    }

    public static  Collection<Integer> sortIntegerCollection(SortAlgoritm<Integer> sortAlgoritm,
                                                             Collection<Integer> collection,
                                                             Comparator<? super Integer> com,
                                                             int start, int end,
                                                             Supplier<Collection<Integer>> collectionSupplier){

        Integer[] sortedMass=  collection.toArray(Integer[]::new);
        sortAlgoritm.Sort(sortedMass,com,start,end);
        return Arrays.stream(sortedMass).collect(Collectors.toCollection(collectionSupplier));
    }


    public static  <T>void quickSort(@NotNull T[] mass, Comparator<? super T> comparator, int start , int end){

        if (start >= end) //завершить выполнение если уже нечего делить
            return;

        int i = start, j = end;
        T centreElement;

        centreElement = mass[start+ (end-start)/2 ];		// центральный элемент
        // процедура разделения
        while ( i<=j ){
            while (comparator.compare(centreElement,mass[i])>0) i++;
            while ( comparator.compare(mass[j], centreElement)>0 ) j--;

            if (i <= j) {
                ArrayUtils.swap(mass,i,j);
                i++;
                j--;
            }
        }
        // рекурсивные вызовы, если есть, что сортировать
        if ( j-start > 0 ) quickSort(mass,comparator,start, j);
        if ( end-j>0 ) quickSort(mass,comparator,i, end);
    }

    public static <T> void piramidSort(@NotNull T[] mass, Comparator<? super T> comparator, int start , int end){

        if((start<0||start>=mass.length)||(end<0||end>mass.length)||(start>end))throw new NullPointerException();

        for(int i=end;i>start;i--)
        {
            for(int j = (i-start)/2; j >=0; j--) {
                if (start + 2 * j + 2 <= i) if (comparator.compare(mass[j], mass[start + 2 * j + 2]) < 0) {
                    ArrayUtils.swap(mass,start + j,start + j * 2 + 2);
                }
                if (start + 2 * j + 1 <= i) if (comparator.compare(mass[start + j], mass[start + 2 * j + 1]) < 0) {
                    ArrayUtils.swap(mass,start + j,start + j * 2 + 1);
                }
            }
            ArrayUtils.swap(mass,start,i);
        }
    }
    public static <T> void interflowSort(@NotNull T[] mass, Comparator<? super T> comparator, int start, int end){

        if (start >= end) //завершить выполнение если уже нечего делить
            return;

            interflowSort(mass,comparator,start, start + (end - start) / 2);
            interflowSort(mass,comparator,start + (end - start) / 2 + 1, end);

            int i=start,j=start+(end-start)/2+1;
            T temp;
            while (i!=j&&j<=end)
            {
                if(comparator.compare(mass[i],mass[j])<0)
                    i++;
                else {
                    temp = mass[j];
                    if (j - i >= 0)
                        System.arraycopy(mass, i, mass, i + 1, j - i);

                    mass[i] = temp;
                    i++;
                    j++;
                }
            }
    }

    public void quickSort(Integer[] mass){
        EfectivSort.quickSort(mass, Comparator.comparingInt(o -> o), 0, mass.length-1);
    }

    public void piramidSort(Integer[] mass){
        piramidSort(mass, Comparator.comparingInt(o -> o), 0, mass.length-1);
    }

    public void interflowSort(Integer[] mass){
        interflowSort(mass, Comparator.comparingInt(o -> o), 0, mass.length-1);
    }

    //сортировка любой подпоследовательности
    public void quickSort(Integer[] mass, int start, int end) {
        quickSort(mass, Comparator.comparingInt(o -> o), start, end);
    }

    public void piramidSort(Integer[] mass, int start, int end){
        piramidSort(mass, Comparator.comparingInt(o -> o), start, end);
    }

    public void interflowSort(Integer[] mass, int start, int end){
        interflowSort(mass, Comparator.comparingInt(o -> o), start, end);
    }

    //сортировка массивов с любыми типами данных

    public <T>void quickSort(T[] mass, Comparator<? super T> com){
        quickSort(mass,com, 0, mass.length-1);
    }

    public <T>void piramidSort(T[] mass, Comparator<? super T> com){
        piramidSort(mass,com, 0, mass.length-1);
    }

    public <T>void interflowSort(T[] mass, Comparator<? super T> com){
        interflowSort(mass,com, 0, mass.length-1);
    }


}
