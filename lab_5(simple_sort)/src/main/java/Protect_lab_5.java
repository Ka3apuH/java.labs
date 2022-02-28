import java.util.*;
import java.util.stream.Collectors;

public class Protect_lab_5 {

    public static void main(String[] args) {
        LinkedList<String> list=new LinkedList<>();

        list.add("dfveveb");
        list.add("evervev");
        list.add("efvefvefv");
        list.add("revebvv");

        String s= list.listIterator(1).previous();// интерфейс listIterator позволяет работать  с LinkedList как с двусвязным списком
        // получение предыдущего элемента для [1] элемента => [0] элемент
        String[] listArray= list.toArray(String[]::new);

        for (String s1:
                (sortLinkedList(list,Comparator.comparingInt(String::hashCode)).toArray(String[]::new))){
            System.out.println(s1+"with heshCode: "+s1.hashCode());
        };

    }

    public static <E> LinkedList<E> sortLinkedList(LinkedList<E> list, Comparator<E> comp) {

        if(list.size()==0)throw new NullPointerException("пустой список");

            E[] mass =(E[]) list.stream().toArray(Object[]::new);
            SimpleSort.selectionSort(mass,comp,0, list.size());
            return Arrays.stream(mass).collect(Collectors.toCollection(() -> new LinkedList<>()));

    }
}
