package ExceptionsOfBinaryTree;

import BinaryTree.BinaryTree;
import Features.StringArrayConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundElementOfTreeExceptionTest {

    private BinaryTree<String> binaryTree;
    private Comparator<String> com;

    @BeforeEach
    void setUp()
    {
        this.com= Comparator.comparingInt(s -> s.hashCode() + s.length());
        this.binaryTree= new BinaryTree<String>(com);
    }

    @AfterEach
    void tearDown()
    {
        this.binaryTree.dellALLElements();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "testRes.csv",numLinesToSkip = 1)
    void notFoundElementOfTreeExceptionTest(@ConvertWith(StringArrayConverter.class) String[] args)
    {

        BinaryTree<String> binaryTree =new BinaryTree<>(com);

        for (String s:args)this.binaryTree.addElement(s);

        StringBuffer NotFoundElement=new StringBuffer();

        for (String s:args)NotFoundElement.append(s);



        Throwable th= assertThrows(NotFoundElementOfTreeException.class, ()->binaryTree.searchElementOfTree(NotFoundElement.toString()));
       assertEquals(th.getMessage(),("NotFoundElement: "+ NotFoundElement));
    }


}