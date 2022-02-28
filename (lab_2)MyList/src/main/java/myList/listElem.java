package myList;

public class listElem<T> {

    T information;
     listElem<T> nextElem;

    public listElem() {
        this.nextElem=null;
        this.information=null;
    }

    public listElem( listElem<T> copyElem) {
        this.information = copyElem.information;
        this.nextElem=copyElem.nextElem;
    }

    public listElem(T information, listElem<T> nextElem) {
        this.information = information;
        this.nextElem=nextElem;
    }

    public listElem(T information) {
        this.information = information;
        this.nextElem=null;
    }

    public T getInformation() {
        return this.information;
    }

    public listElem<T> getnextElem() {
        return this.nextElem;
    }
}
