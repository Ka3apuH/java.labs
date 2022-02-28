import MyVector.myVector;
import Features.StringArrayConverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;


public class myVectorTest {

    // проверка конструктора
    @Test
    void constructorTest() {
        myVector<String> vector = new myVector<>();
        Assertions.assertEquals(vector.getCurrentNumberOfElements(), 0);
        Assertions.assertEquals(vector.getMaxMumberOfElements(), 10);
    }

    //проверка конструктора с возможностью задания начального числа элементов
    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @ValueSource(ints = {10, 12, 99, 1, 0, 2})
    void constructorWithNumberOfElemensTest(int number) {

        myVector<String> vector = new myVector<>(number);
        Assertions.assertEquals(vector.getCurrentNumberOfElements(), number);
        for (int i = 0; i < vector.getCurrentNumberOfElements(); i++) {
            Assertions.assertNull(vector.getElement(i));
        }

        Assertions.assertEquals(vector.getMaxMumberOfElements(), 2 * number + 10);
    }

    //проверка добавления элементав конец
    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
     void addElemToTheEndTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myVector<String> vector = new myVector<>();
        for (String s : inf) {
            vector.addElemToTheEnd(s);
        }
        Assertions.assertEquals(vector.getCurrentNumberOfElements(), inf.length);

        for (int i = 0; i < vector.getCurrentNumberOfElements(); i++) {
            Assertions.assertEquals(inf[i], vector.getElement(i));
        }

    }

    //проверка конструктора копирования
    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    void constructorWithNumberOfElemensTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myVector<String> vector = new myVector<>();
        for (String s : inf) {
            vector.addElemToTheEnd(s);
        }
        myVector<String> copyOfVector = new myVector<>(vector);

        Assertions.assertEquals(vector.getCurrentNumberOfElements(), copyOfVector.getCurrentNumberOfElements());
        Assertions.assertEquals(vector.getMaxMumberOfElements(), copyOfVector.getMaxMumberOfElements());

        for (int i = 0; i < vector.getCurrentNumberOfElements(); i++) {
            Assertions.assertEquals(copyOfVector.getElement(i), vector.getElement(i));
        }

    }

    //тест удаления последнегоэлемента
    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    void delElemenfFromEndTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        if (inf.length == 0) return;

        myVector<String> vector = new myVector<>();
        for (String s : inf) {
            vector.addElemToTheEnd(s);
        }
        vector.delElemenfFromEnd();

        Assertions.assertEquals(vector.getCurrentNumberOfElements(), inf.length - 1);
        Assertions.assertEquals(vector.getMaxMumberOfElements(), vector.getMaxMumberOfElements());
        if (inf.length > 1)
            Assertions.assertEquals(vector.getElement(vector.getCurrentNumberOfElements() - 1), inf[inf.length - 2]);
    }

    //тест удаления элемента по индексу
    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    void delElemenfByIndexTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        if (inf.length == 0) return;
        myVector<String> vector = new myVector<>();

        for (int i = 0; i < inf.length - 1; i++) {

            for (String s : inf) {
                vector.addElemToTheEnd(s);
            }

            vector.delElemenfByIndex(i);

            Assertions.assertEquals(vector.getCurrentNumberOfElements(), inf.length - 1);
            Assertions.assertEquals(vector.getMaxMumberOfElements(), vector.getMaxMumberOfElements());
            if (inf.length > 1) Assertions.assertEquals(vector.getElement(i), inf[i + 1]);
            vector.dellAllElements();
        }
        for (String s : inf) {
            vector.addElemToTheEnd(s);
        }
        vector.delElemenfByIndex(inf.length - 1);

        Assertions.assertEquals(vector.getCurrentNumberOfElements(), inf.length - 1);
        Assertions.assertEquals(vector.getMaxMumberOfElements(), vector.getMaxMumberOfElements());
        if (inf.length > 1)
            Assertions.assertEquals(vector.getElement(vector.getCurrentNumberOfElements() - 1), inf[inf.length - 2]);

    }

    //тест вставки элемента по индексу;
    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    public void addElemenfByIndexTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myVector<String> vector = new myVector<>();

        for (int i = 0; i < inf.length; i++) {

            for (String s : inf) {
                vector.addElemToTheEnd(s);
            }

            vector.addElemenfByIndex(i, "STRING");

            Assertions.assertEquals(vector.getCurrentNumberOfElements(), inf.length + 1);
            Assertions.assertEquals(vector.getElement(i), "STRING");
            for (int j = 0; j < vector.getCurrentNumberOfElements(); j++) {
                if(j==i)continue;
                if(j<i)Assertions.assertEquals(vector.getElement(j),inf[j]);
                if(j>i)Assertions.assertEquals(vector.getElement(j),inf[j-1]);
            }
            vector.dellAllElements();
        }
    }
// тест изменения размера массива

    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    public void resizingArrayTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myVector<String> vector = new myVector<>();
        int numberOfNewSize =(int) (Math.random()*20+inf.length);
        for (String s : inf) {
            vector.addElemToTheEnd(s);
        }

        vector.resizingArray(numberOfNewSize);

        Assertions.assertEquals(vector.getCurrentNumberOfElements(), numberOfNewSize);
        for (int j = inf.length; j < vector.getCurrentNumberOfElements(); j++) {
            Assertions.assertNull(vector.getElement(j));
        }
    }
}
