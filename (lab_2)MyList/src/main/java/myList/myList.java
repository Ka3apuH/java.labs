package myList;

import ExceptionsOfMyList.NotFoundCopyListException;
import ExceptionsOfMyList.NotFoundElementOfListException;

public class myList<ClassName> {

    private int numberOfElemens;
    public listElem<ClassName> firstElement;
    public listElem<ClassName> endedElement;

////конструктор
public myList() {
    this.firstElement=null;
    this.endedElement=null;
    this.numberOfElemens=0;
}
    public myList(ClassName inf) {
        this.firstElement=new listElem<>(inf);
        this.firstElement.nextElem=null;
        this.endedElement=this.firstElement;

        this.numberOfElemens=1;
    }

    ////конструктор копирования
    public myList( myList<ClassName> copyList) throws NotFoundCopyListException {

        if (copyList == null) {
            throw new NotFoundCopyListException("NotFoundCopyList");
        }

        if(copyList.numberOfElemens == 0){
            this.firstElement=null;
            this.endedElement=null;
            this.numberOfElemens=0;
            return;
        }

        listElem<ClassName> helpElem=new listElem<ClassName>(copyList.firstElement);
        this.addElemToEndOfList(helpElem.information);
        for (int i=0;i<copyList.numberOfElemens-1;i++)
        {
            helpElem =  helpElem.nextElem;
            this.addElemToEndOfList(helpElem.information);
        }

        this.endedElement=helpElem;
        this.endedElement.nextElem=null;

    }

    // добавление элемента в начало списка
    public void addElemToBeginOfList(ClassName inf){
        if(this.firstElement==null)
        {
            this.firstElement= new listElem<>(inf);
            this.endedElement=this.firstElement;
        }else {
            this.firstElement = new listElem<ClassName>(inf, this.firstElement);
        }

        this.numberOfElemens++;
    }

    public void addElemToBeginOfList() {
        if(this.firstElement==null)
        {
            this.firstElement=new listElem<ClassName>();
            this.endedElement=this.firstElement;
        }else {
            this.firstElement = new listElem<ClassName>(null, this.firstElement);
        }
        this.numberOfElemens++;
    }
    ///////////////////////////////

    // добавление  элемента в конец
    public void addElemToEndOfList(ClassName inf){
        if(this.firstElement==null&&this.endedElement==null)
        {
            this.firstElement=new listElem<>(inf);
            this.endedElement=this.firstElement;
        }else {
            this.endedElement.nextElem = new listElem<>(inf);
            this.endedElement=this.endedElement.nextElem;
        }
        this.numberOfElemens++;

    }

    public void addElemToEndOfList() {
        if(this.firstElement==null&&this.endedElement==null)
        {
            this.firstElement=new listElem<ClassName>();
            this.endedElement=this.firstElement;
        }else {

            this.endedElement.nextElem = new listElem<ClassName>();
            this.endedElement=this.endedElement.nextElem;
        }
        this.numberOfElemens++;
    }
    //////////////////////////////

    // удаление элемента из начала
    public void delElemFromBegin() throws NotFoundElementOfListException {
        if(this.numberOfElemens==0)throw new NotFoundElementOfListException("ListIsEmpty");
        this.numberOfElemens--;
        this.firstElement.information=null;
        this.firstElement=this.firstElement.nextElem;
        if(this.getNumberOfElement()==0)this.endedElement=null;
    }

    // удаление элемента по значению
    public void delElemByvalue(ClassName inf) throws NotFoundElementOfListException {

        if(this.numberOfElemens==0) throw new NotFoundElementOfListException("ListIsEmpty");

        int beginNumberOfElement=this.numberOfElemens;

        while (this.firstElement.information.equals(inf)) {
            this.delElemFromBegin();
            if(this.getNumberOfElement()==0)return;
        }

        listElem<ClassName> helpElement2=new listElem<ClassName>(this.firstElement);
        listElem<ClassName> helpElement1=new listElem<ClassName>(this.firstElement.nextElem);


        for (int i = 0; i <this.numberOfElemens-1 ; i++) {



            if(helpElement1.information.equals(inf)){
                this.numberOfElemens--;
                i--;
                helpElement2.nextElem=helpElement1.nextElem;
                helpElement1.information=null;
                helpElement1.nextElem=null;
                helpElement1=helpElement2.nextElem;
                continue;
            }

            helpElement1=helpElement1.nextElem;
            helpElement2=helpElement2.nextElem;
        }

        if(beginNumberOfElement==this.numberOfElemens)throw new NotFoundElementOfListException("ElementNotFound");
    }
//удаление элемента с конца
    public void delElemFromEnd() throws NotFoundElementOfListException {

        if(this.numberOfElemens==0)throw new NotFoundElementOfListException("ListIsEmpty");
        this.endedElement.information=null;
        this.numberOfElemens--;
        if(this.numberOfElemens==0){
            this.endedElement=null;
            this.firstElement=null;
            return;
        }
        this.endedElement=this.firstElement;
        for (int i = 0; i < this.numberOfElemens-1; i++) this.endedElement=this.endedElement.nextElem;

    }


    //////////////////////////////

// удаление всех элементов
public void delAllElemnts() {
    if(this.numberOfElemens==0)return;
    this.delElemFromBegin();
    delAllElemnts();
}

    /*public void printOk() {
        if(this.numberOfElemens==0)return;
        listElem<ClassName> helpElForList=new listElem<ClassName>(this.firstElement);
        System.out.println(helpElForList.getInformation());
        for (int i = 0; i <this.getNumberOfElement()-1 ; i++) {
            helpElForList=helpElForList.getnextElem();
            System.out.println(helpElForList.getInformation());

        }
    }*/

    //получение количества элементов
    public int getNumberOfElement() {
        return this.numberOfElemens;
    }


}
