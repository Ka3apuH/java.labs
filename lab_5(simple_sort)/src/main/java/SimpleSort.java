import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class SimpleSort {

    public static  <T>void  selectionSort(@NotNull T[] mass, Comparator<? super T> comparator,int start ,int end) throws NullPointerException{

        if((start<0||start>=mass.length)||(end<0||end>mass.length)||(start>end))throw new NullPointerException();
        T min;

        for (int i=start; i <end ; i++) {
            min=mass[i];
            for (int j = i+1; j < end ; j++) {
                if(comparator.compare(min,mass[j])>0){
                    mass[i]=mass[j];
                    mass[j]=min;
                    min=mass[i];
                }
            }
        }
    }
    public static  <T> void  insertSort(@NotNull T[] mass, Comparator<? super T> comparator,int start ,int end) throws NullPointerException{

        if((start<0||start>=mass.length)||(end<0||end>mass.length)||(start>end))throw new NullPointerException();

        T temp;

        for (int i=start+1; i <end ; i++) {
            temp=mass[i];
            for (int j = i-1; j>-1 && comparator.compare(mass[j],temp)>0; j--) {
                mass[j+1]=mass[j];
                mass[j]=temp;
            }
        }
    }
    public static <T> void  exchangeSort(@NotNull T[] mass, Comparator<? super T> comparator,int start ,int end) throws NullPointerException{

        if((start<0||start>=mass.length)||(end<0||end>mass.length)||(start>end))throw new NullPointerException();
        T helpEl;

        for (int i=start; i <end ; i++) {
            for (int j = 1; j < end ; j++) {
                if(comparator.compare(mass[j-1],mass[j])>0){
                    helpEl =mass[j-1];
                    mass[j-1]= mass[j];
                    mass[j]=helpEl;


                }
            }
        }
    }

    public void selectionSort(Integer[] mass)throws NullPointerException{
        SimpleSort.selectionSort(mass, Comparator.comparingInt(o -> o), 0, mass.length);
    }

    public void insertSort(Integer[] mass)throws NullPointerException{
        insertSort(mass, Comparator.comparingInt(o -> o), 0, mass.length);
    }

    public void exchangeSort(Integer[] mass)throws NullPointerException{
        exchangeSort(mass, Comparator.comparingInt(o -> o), 0, mass.length);
    }

    //сортировка любой подпоследовательности
    public void selectionSort(Integer[] mass,int start,int end) throws NullPointerException{
        selectionSort(mass, Comparator.comparingInt(o -> o), start, end);
    }

    public void insertSort(Integer[] mass,int start,int end)throws NullPointerException{
        insertSort(mass, Comparator.comparingInt(o -> o), start, end);
    }

    public void exchangeSort(Integer[] mass,int start,int end)throws NullPointerException{
        exchangeSort(mass, Comparator.comparingInt(o -> o), start, end);;
    }

    //сортировка массивов с любыми типами данных

    public <T>void selectionSort(T[] mass,Comparator<? super T> com)throws NullPointerException{
        selectionSort(mass,com, 0, mass.length);
    }

    public <T>void insertSort(T[] mass,Comparator<? super T> com)throws NullPointerException{
        insertSort(mass,com, 0, mass.length);
    }

    public <T>void exchangeSort(T[] mass,Comparator<? super T> com)throws NullPointerException{
        exchangeSort(mass,com, 0, mass.length);
    }

}
