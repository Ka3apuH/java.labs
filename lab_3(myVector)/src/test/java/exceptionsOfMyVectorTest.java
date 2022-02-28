import Features.StringArrayConverter;
import MyVector.myVector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

public class exceptionsOfMyVectorTest {
    @Test
    void testExceptionFromDelElemenfFromEnd () {

        myVector<String> vector =new myVector<>();
        Throwable th= Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, vector::delElemenfFromEnd);
        Assertions.assertEquals(th.getMessage(),"vectorIsEmpty");
    }

    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    void testExceptionFromdelElemenfByIndex(@ConvertWith(StringArrayConverter.class) String[] inf){

        myVector<String> vector = new myVector<>();
        int numberOfDellElement=(int) (Math.random()*20+inf.length);
            for (String s : inf) {
                vector.addElemToTheEnd(s);
            }

        Throwable th= Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->vector.delElemenfByIndex(numberOfDellElement));
        Assertions.assertEquals(th.getMessage(),numberOfDellElement + " - invalidIndex");
    }

    @ParameterizedTest(name = "{index} строчка элементов из src/test/java/testResources/ResourcesForTestsFile.csv")
    @CsvFileSource(resources = "ResourcesForTestsFile.csv")
    public void testExceptionFromaddElemenfByIndex(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myVector<String> vector = new myVector<>();
        int numberOfAddElement =(int) (Math.random()*20+inf.length);
            for (String s : inf) {
                vector.addElemToTheEnd(s);
            }

        Throwable th= Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->vector.addElemenfByIndex(numberOfAddElement, "STRING"));

        Assertions.assertEquals(th.getMessage(), numberOfAddElement + " - invalidIndex");

    }

}
