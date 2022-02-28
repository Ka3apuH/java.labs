import myList.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;


class myListTest{
    ///тест конструктора копирования
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void constractorOfCopyTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myList<String> list=new myList<String>();

        for (String s : inf) {
            list.addElemToBeginOfList(s);
        }
        myList<String> copyList=new myList<String>(list);

        listElem<String> helpElForList=new listElem<String>(list.firstElement);
        listElem<String> helpElForCopyList=new listElem<String>(copyList.firstElement);

        Assertions.assertEquals(list.getNumberOfElement(),copyList.getNumberOfElement());
        for (int i = 0; i <list.getNumberOfElement() ; i++) {

            Assertions.assertEquals(helpElForCopyList.getInformation(),helpElForList.getInformation());
            helpElForCopyList=helpElForCopyList.getnextElem();
            helpElForList=helpElForList.getnextElem();
        }
        list.delAllElemnts();
        copyList.delAllElemnts();
    }


    ////тест на добавление в начало списка
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void addElemToBeginOfListTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myList<String> list=new myList<String>();
        for (String s : inf) {
            list.addElemToBeginOfList(s);
        }

        Assertions.assertEquals((list.firstElement.getInformation()),inf[inf.length-1]);
        list.delAllElemnts();
    }

    //тест на добавление элемента в конец
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void addElemToEndOfListTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myList<String> list=new myList<String>();
        for (String s : inf) {
            list.addElemToEndOfList(s);
        }

        Assertions.assertEquals((list.endedElement.getInformation()),inf[inf.length-1]);
        Assertions.assertEquals((list.firstElement.getInformation()),inf[0]);

        list.delAllElemnts();
    }
    // тест удаления элемента из начала
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void delElemFromBeginTest(@ConvertWith(StringArrayConverter.class) String[] inf){

        myList<String> list=new myList<String>();
        for (String s : inf) {
            list.addElemToEndOfList(s);
        }
        list.delElemFromBegin();
        Assertions.assertEquals((list.getNumberOfElement()+1),inf.length);
        if(inf.length>1)Assertions.assertEquals((list.firstElement.getInformation()),inf[1]);
        else{
            Assertions.assertNull(list.firstElement);
            Assertions.assertNull(list.endedElement);
        }

        list.delAllElemnts();
    }

    // тест удаления элемента по значению
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt;dddddd;rrrrrrr;eeeeeee"})
    void delElemByvalueTest(@ConvertWith(StringArrayConverter.class) String[] inf){

        myList<String> list =new myList<String>();
        for (String s : inf) {
            list.addElemToEndOfList(s);
        }

        int numberOfDelElement=(int) (Math.random()*(inf.length-1));
        int repeat=0;
        for (String s : inf) {
            if(inf[numberOfDelElement].equals(s))repeat++;
        }

        list.delElemByvalue(inf[numberOfDelElement]);
        if(inf.length==repeat){
            Assertions.assertNull(list.endedElement);
            Assertions.assertNull(list.firstElement);
            return;
        }

        Assertions.assertEquals((list.getNumberOfElement()+repeat),inf.length);

        listElem<String> helpElForList=new listElem<String>(list.firstElement);

        for (int i = 0; i <list.getNumberOfElement() ; i++) {
            Assertions.assertNotEquals(helpElForList.getInformation(),inf[numberOfDelElement]);
        }

        list.delAllElemnts();
    }

    // тест удаления элемента из начала
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void delElemFromEndTest(@ConvertWith(StringArrayConverter.class) String[] inf){

        myList<String> list=new myList<String>();
        for (String s : inf) {
            list.addElemToEndOfList(s);
        }
        list.delElemFromEnd();

        Assertions.assertEquals((list.getNumberOfElement()+1),inf.length);
        if(inf.length>1)Assertions.assertEquals((list.endedElement.getInformation()),inf[inf.length-2]);
        else {
            Assertions.assertNull(list.firstElement);
            Assertions.assertNull(list.endedElement);
        }

        list.delAllElemnts();
    }
///тест удаления всех элементов
    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void delAllElemntsTest(@ConvertWith(StringArrayConverter.class) String[] inf) {

        myList<String> list=new myList<String>();
        for (String s : inf) {
            list.addElemToEndOfList(s);
        }
        list.delAllElemnts();
        Assertions.assertNull(list.firstElement);
        Assertions.assertNull(list.endedElement);

    }


    @ParameterizedTest
    @CsvSource(value = {"inf;gbtbtb;tbrthbnyhhn;hnnyhhnhyn","wfwfw","rgtg;tvrvrtv;rtvrtvr;rtvrvbb;rgvrbtbtv;rgvrvvtvtbtb;rrgbtybt"})
    void getNumberOfElement(@ConvertWith(StringArrayConverter.class) String[] inf) {
        myList<String> list=new myList<String>();
        for (String s : inf) {
            list.addElemToEndOfList(s);
        }
        Assertions.assertEquals(list.getNumberOfElement(),inf.length);
    }
}