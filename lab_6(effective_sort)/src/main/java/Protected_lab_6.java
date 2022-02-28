import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Protected_lab_6 {


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

    public static int solotion(Integer[] mass){
        interflowSort(mass,Comparator.comparingInt(Integer::intValue),0, mass.length-1);
        int k=0;
        for (int i = 0; i < mass.length-1; i++) {
            Integer temp=mass[i];
            if(temp.equals(mass[i+1]))
            {
                System.out.println(mass[i]);
                k=1;
            }
            while (i<mass.length&&mass[i].equals(temp))i++;
            i--;
        }
        if(k==0)return -1;
        return 1;
    }

    public static void main(String[] args) {
        Integer[] ch=new Integer[]{0,34,23,25,5,23,5,87,87,2,2,2,2,5,5,4,6,0,19};
        int k=solotion(ch);
    }
}
