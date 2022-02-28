package MyVector;

public class myVector<ClassTipe>  {

    private ClassTipe []pointVector;
    private  int currentNumberOfElements;
    private  int maxMumberOfElements;

//конструктор

    @SuppressWarnings("unchecked")
    public myVector() {
        this.maxMumberOfElements =10;
        this.currentNumberOfElements=0;
        try {
            this.pointVector= (ClassTipe[]) new Object [this.maxMumberOfElements];
        } catch (ClassCastException  e)
        {
            e.getStackTrace();
        }
    }

    //конструктор с возможностью задания начального числа элементов
    @SuppressWarnings("unchecked")
    public myVector(int numberOfElemensNow) {
        this.currentNumberOfElements=numberOfElemensNow;

        this.maxMumberOfElements = 2*numberOfElemensNow+10;
        try {
             this.pointVector= (ClassTipe[]) new Object [this.maxMumberOfElements];
        } catch (ClassCastException  e)
        {
            e.getStackTrace();
        }
        for (int i = 0; i <this.currentNumberOfElements-1 ; i++) {
            this.pointVector[i]=null;
        }
    }

    // конструктор копирования
    @SuppressWarnings("unchecked")
    public myVector(myVector<ClassTipe> copyVector) {

        this.currentNumberOfElements=copyVector.currentNumberOfElements;
        this.maxMumberOfElements =copyVector.maxMumberOfElements;


        try {
            this.pointVector= (ClassTipe[]) new Object [this.maxMumberOfElements];
        } catch (ClassCastException  e)
        {
            e.getStackTrace();
        }
        System.arraycopy(copyVector.pointVector,0,this.pointVector,0,copyVector.currentNumberOfElements);
    }

    @SuppressWarnings("unchecked")
    public void addElemToTheEnd(ClassTipe inf)
    {
        if(this.currentNumberOfElements==this.maxMumberOfElements){
            this.maxMumberOfElements=2*(this.maxMumberOfElements+1)+10;

            ClassTipe[] helpArray=null;

            try {
                 helpArray= (ClassTipe[]) new Object [this.maxMumberOfElements];
            } catch (ClassCastException  e)
            {
                e.getStackTrace();
            }

            System.arraycopy(this.pointVector,0,helpArray,0,this.currentNumberOfElements);
            helpArray[this.currentNumberOfElements]=inf;
            this.currentNumberOfElements++;

            for (int i = 0; i <this.currentNumberOfElements-1 ; i++) {
                this.pointVector[i]=null;
            }

            this.pointVector=helpArray;
            return;
        }
        if(this.currentNumberOfElements<this.maxMumberOfElements){
            this.pointVector[this.currentNumberOfElements]=inf;
            this.currentNumberOfElements++;
        }
    }

    //удаление последнего элемента;
public void delElemenfFromEnd()throws ArrayIndexOutOfBoundsException{
        if(this.currentNumberOfElements==0)throw new ArrayIndexOutOfBoundsException("vectorIsEmpty");
        this.currentNumberOfElements--;
        this.pointVector[this.currentNumberOfElements]=null;
}

    //удаление элемента по индексу;
    public void delElemenfByIndex(int index) throws ArrayIndexOutOfBoundsException{

        if(index>=this.currentNumberOfElements||index<0)
            throw new ArrayIndexOutOfBoundsException(index+" - invalidIndex");

        this.currentNumberOfElements--;
        this.pointVector[index]=null;

        System.arraycopy(this.pointVector,index+1,this.pointVector,index,this.currentNumberOfElements- index);
    }

    //вставка элемента по индексу;
    public void addElemenfByIndex(int index,ClassTipe inf) throws ArrayIndexOutOfBoundsException{

        if(index>=this.currentNumberOfElements||index<0)
            throw new ArrayIndexOutOfBoundsException(index+" - invalidIndex");

        if(this.currentNumberOfElements==this.maxMumberOfElements)this.addElemToTheEnd(null);
        else {
            this.currentNumberOfElements++;
        }

        System.arraycopy(this.pointVector,index,this.pointVector,index+1,this.currentNumberOfElements- index);
        this.pointVector[index]=inf;
    }

    //изменение размера массива;

    @SuppressWarnings("unchecked")
    public void resizingArray(int newSize) {

        if(newSize>this.maxMumberOfElements){

            this.maxMumberOfElements=2*(1 + newSize)+10;
            ClassTipe[] helpArray=null;

            try {
                helpArray= (ClassTipe[]) new Object [this.maxMumberOfElements] ;
            } catch (ClassCastException  e)
            {
                e.getStackTrace();
            }

            System.arraycopy(this.pointVector,0,helpArray,0,this.currentNumberOfElements);

            for (int i = 0; i <this.currentNumberOfElements-1 ; i++) {
                this.pointVector[i]=null;
            }
            this.pointVector=helpArray;
        }
        for (int i = this.currentNumberOfElements-1; i < newSize; i++) {
            this.pointVector[i]=null;
        }
        this.currentNumberOfElements=newSize;


    }

//очистка массива
    public void dellAllElements(){
        for (int i = 0; i <this.currentNumberOfElements ; i++) {
            this.pointVector[i]=null;
        }
        this.currentNumberOfElements=0;
    }

    // получение текущего количества  элементов
    public int getCurrentNumberOfElements() {
        return currentNumberOfElements;
    }
    // получение max количества  элементов
    public int getMaxMumberOfElements() {
        return maxMumberOfElements;
    }

    //получение I элемента meVector
    public ClassTipe getElement(int I) throws ArrayIndexOutOfBoundsException{
        if(I>(this.currentNumberOfElements-1)||I<0)throw new ArrayIndexOutOfBoundsException(I+" - invalidIndex");
        return this.pointVector[I];
    }
}


