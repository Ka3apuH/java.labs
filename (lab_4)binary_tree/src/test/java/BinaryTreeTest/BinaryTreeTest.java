package BinaryTreeTest;

import BinaryTree.*;

import static org.junit.jupiter.api.Assertions.*;
import Features.StringArrayConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.Comparator;

class BinaryTreeTest {

    private BinaryTree<String> binaryTree;
    private Comparator<String> com;

    @BeforeEach
    void setUp()
    {
        this.com= Comparator.comparingInt(s -> s.hashCode() + s.length());
        this.binaryTree= new BinaryTree<String>(com);
    }

    @AfterEach
    void tearDown() {
        this.binaryTree.dellALLElements();
    }


    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testRes.csv",numLinesToSkip = 1)
    void addElementTest(@ConvertWith(StringArrayConverter.class) String[] args) {

        for (String s:args) {
            binaryTree.addElement(s);
        }

        Arrays.sort(args,this.com);

        assertArrayEquals(binaryTree.toString().split("\n"),args );
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testRes.csv",numLinesToSkip = 1)
    void getNumberOfElementTest(@ConvertWith(StringArrayConverter.class) String[] args) {

        for (String s:args) {
            binaryTree.addElement(s);
        }
        assertEquals(binaryTree.getNumberOfElement(),args.length);
    }


    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testRes.csv",numLinesToSkip = 1)
    void searchElementOfTreeTest(@ConvertWith(StringArrayConverter.class) String[] args) {

        int numberOfStrFromArgs=(int)(Math.random()*(args.length-1));
        for (String s:args) {
            binaryTree.addElement(s);
        }
        ElementOfBinaryTree<String> searchElementOfTree= binaryTree.searchElementOfTree(args[numberOfStrFromArgs]);
        assertEquals(searchElementOfTree.getInf(),args[numberOfStrFromArgs]);
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testRes.csv",numLinesToSkip = 1)
    void dellALLElementsTest(@ConvertWith(StringArrayConverter.class) String[] args) {

        for (String s:args) {
            binaryTree.addElement(s);
        }


        binaryTree.dellALLElements();
        assertNull(binaryTree.getTreeRoot());
        assertEquals(binaryTree.getNumberOfElement(),0);
        binaryTree.addElement("aa");
    }

    @ParameterizedTest(name = "[{index}]")
    @CsvFileSource(resources = "testRes.csv",numLinesToSkip = 1)
    void copyConstructorTest(@ConvertWith(StringArrayConverter.class) String[] args) {

        for (String s:args) {
            binaryTree.addElement(s);
        }

        BinaryTree<String> copyBinaryTree=new BinaryTree<String>(binaryTree);

        Arrays.sort(args,this.com);

        assertArrayEquals(copyBinaryTree.toString().split("\n"),args );
        assertEquals(copyBinaryTree.toString(), binaryTree.toString());
    }

}