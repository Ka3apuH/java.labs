package BinaryTree;

import org.jetbrains.annotations.NotNull;
import ExceptionsOfBinaryTree.NotFoundElementOfTreeException;

import java.util.Comparator;

public class BinaryTree<ClassType> {

    private final Comparator<? super ClassType> compareClass;
    private ElementOfBinaryTree<ClassType> treeRoot;
    private int numberOfElement;


    public int getNumberOfElement()
    {
        return numberOfElement;
    }

    public ElementOfBinaryTree<ClassType> getTreeRoot()
    {
        return treeRoot;
    }

    //конструктор необходимо задать как мы будем сравнивать элементы
    public BinaryTree(@NotNull Comparator<? super ClassType> compareClass)
    {
        this.compareClass=compareClass;
        this.numberOfElement=0;
        this.treeRoot=null;
    }
//конструктор создающий из любого элемента дерева дерево
    public BinaryTree(@NotNull ElementOfBinaryTree<ClassType> elementOfBinaryTree,@NotNull Comparator<? super ClassType> compareClass)
    {
        this.compareClass=compareClass;
        this.treeRoot= new ElementOfBinaryTree<ClassType>(elementOfBinaryTree);
        this.treeRoot.parent=null;
        this.numberOfElement=0;

        this.addElementsToTreeAfterGiven(elementOfBinaryTree);

    }

    //конструктор копирования
    public BinaryTree(@NotNull BinaryTree<ClassType> copyTree)
    {

        this.compareClass=copyTree.compareClass;
        this.numberOfElement=0;
        this.addElement(copyTree.treeRoot.inf);

        this.addElementsToTreeAfterGiven(copyTree.treeRoot);
    }

// метод для добавления всех элементов после elementOfBinaryTree(не включая его)
    public void addElementsToTreeAfterGiven(@NotNull ElementOfBinaryTree<ClassType> elementOfBinaryTree)
    {

        if(elementOfBinaryTree.rightChild != null) for(int i = 0; i < elementOfBinaryTree.rightChild.numberOfRepetitions; i++)
            this.addElement(elementOfBinaryTree.rightChild.inf);
        if(elementOfBinaryTree.leftChild != null) for(int i = 0; i < elementOfBinaryTree.leftChild.numberOfRepetitions; i++)
            this.addElement(elementOfBinaryTree.leftChild.inf);

        if(elementOfBinaryTree.rightChild != null)addElementsToTreeAfterGiven(elementOfBinaryTree.rightChild);
        if(elementOfBinaryTree.leftChild != null)addElementsToTreeAfterGiven(elementOfBinaryTree.leftChild);
    }

    //добавление элемента в дерево
    public void addElement(@NotNull ClassType inf)
    {

        if(this.numberOfElement==0)
        {
            this.treeRoot=new ElementOfBinaryTree<>(inf,null);
            this.numberOfElement++;
            return;
        }

        ElementOfBinaryTree<ClassType> elementForCheck=this.treeRoot;

        while (true)
        {
            if(this.compareClass.compare(inf,elementForCheck.inf) >0)
            {
                if(elementForCheck.rightChild==null)
                {
                    elementForCheck.rightChild=new ElementOfBinaryTree<ClassType>(inf,elementForCheck);
                    this.numberOfElement++;
                    return;
                }

                elementForCheck=elementForCheck.rightChild;
                continue;
            }

            if(compareClass.compare(inf,elementForCheck.inf)< 0)
            {
                if(elementForCheck.leftChild==null)
                {
                    elementForCheck.leftChild=new ElementOfBinaryTree<ClassType>(inf,elementForCheck);
                    this.numberOfElement++;
                    return;
                }
                elementForCheck=elementForCheck.leftChild;
                continue;
            }

                elementForCheck.numberOfRepetitions++;
                this.numberOfElement++;
                return;

        }
    }
    // метод поиска элемента
    public ElementOfBinaryTree<ClassType> searchElementOfTree(@NotNull ClassType inf)
    {

        ElementOfBinaryTree<ClassType> searchElement=this.treeRoot;

        while (searchElement!=null)
        {

            if(this.compareClass.compare(inf,searchElement.inf) > 0)
            {
                searchElement=searchElement.rightChild;
            } else if(this.compareClass.compare(inf,searchElement.inf) < 0)
            {
                searchElement=searchElement.leftChild;
            } else
            {
                return searchElement;
            }
        }
        throw new NotFoundElementOfTreeException("NotFoundElement: "+ inf);
    }

    //удаление элементов после заданного
    public void delElementsAfterGiven(@NotNull ElementOfBinaryTree<ClassType> elementOfBinaryTree)
    {

        if(elementOfBinaryTree.leftChild!=null)delElementsAfterGiven(elementOfBinaryTree.leftChild);
        if(elementOfBinaryTree.rightChild!=null)delElementsAfterGiven(elementOfBinaryTree.rightChild);


        ElementOfBinaryTree<ClassType> helpForDel=elementOfBinaryTree.parent;

        if(elementOfBinaryTree.parent==null)return;

        this.numberOfElement-=elementOfBinaryTree.numberOfRepetitions;

        if(elementOfBinaryTree==helpForDel.rightChild) helpForDel.rightChild = null;

        else
        {
            helpForDel.leftChild = null;
        }

    }

    //ввывод элементов после заданного
    public StringBuffer ElementsAfterGiventoString(@NotNull ElementOfBinaryTree<ClassType> elementOfBinaryTree)
    {

        StringBuffer s=new StringBuffer();

        if(elementOfBinaryTree.leftChild!=null)
        {
            s.append(ElementsAfterGiventoString(elementOfBinaryTree.leftChild));
            s.append("\n");
        }

        s.append(elementOfBinaryTree.inf);

        for (int i = 0; i <elementOfBinaryTree.numberOfRepetitions-1 ; i++)
        {
            s.append("\n");
            s.append(elementOfBinaryTree.inf);
        }

        if(elementOfBinaryTree.rightChild != null)
        {
            s.append("\n");
            s.append(ElementsAfterGiventoString(elementOfBinaryTree.rightChild));
        }

        return s;

    }

    @Override
    public String toString()
    {
        return ElementsAfterGiventoString(this.treeRoot).toString();
    }

    //метод удаления всех элементов
    public void dellALLElements(){
        delElementsAfterGiven(this.treeRoot);
        this.treeRoot=null;
        this.numberOfElement--;
    }

}
