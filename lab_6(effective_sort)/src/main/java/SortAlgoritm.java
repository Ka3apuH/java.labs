import java.util.Comparator;

@FunctionalInterface
public interface SortAlgoritm<T> {
    public void Sort(T[]mass, Comparator<?super T> comparator,int start,int end);
}
