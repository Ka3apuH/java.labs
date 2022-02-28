import myList.*;
import ExceptionsOfMyList.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

public class ExceptionsOfMyListTest {
    @ParameterizedTest
    @NullSource
    void testNotFoundCopyListException (myList<String> cr) {

        Throwable th= Assertions.assertThrows(NotFoundCopyListException.class, ()->{
            myList<String> list=new myList<String>(cr);
        });
        Assertions.assertEquals(th.getMessage(),"NotFoundCopyList");
    }


    @ParameterizedTest
    @CsvSource({"fdvfevfev","erververvv","ervevev"})
    void testNotFoundElementOfListException (String value) {

        myList<String> list=new myList<String>();

        Throwable th= Assertions.assertThrows(NotFoundElementOfListException.class, ()->{
            list.delElemFromBegin();
        });
        Assertions.assertEquals(th.getMessage(),"ListIsEmpty");

        th= Assertions.assertThrows(NotFoundElementOfListException.class, ()->{
            list.delElemFromEnd();
        });
        Assertions.assertEquals(th.getMessage(),"ListIsEmpty");

        th= Assertions.assertThrows(NotFoundElementOfListException.class, ()->{
            list.delElemByvalue(value);
        });
        Assertions.assertEquals(th.getMessage(),"ListIsEmpty");


    }

    @ParameterizedTest
    @CsvSource(value = {"fdvfevfev;erververvv;ervevev;ecwevwev;wrevewvwv;werverv;dfvfdvfv;vfvfb;fdsvdfbfb","fvfdvfv;ervbervberb;erberberb;erbverberberb;rerberberberb"})
    void testNotFoundElementOfListException2 (@ConvertWith(StringArrayConverter.class) String[] inf) {

        myList<String> list=new myList<String>();

        for (String s : inf) {
            list.addElemToBeginOfList(s);
        }

        Throwable th= Assertions.assertThrows(NotFoundElementOfListException.class, ()->{
            list.delElemByvalue("123444");
        });
        Assertions.assertEquals(th.getMessage(),"ElementNotFound");
    }
}
