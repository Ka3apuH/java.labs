package BinaryTree;

public class ElementOfBinaryTree<ClassType>{

    final ClassType  inf;
    int numberOfRepetitions;
    ElementOfBinaryTree<ClassType> parent;
    ElementOfBinaryTree<ClassType> leftChild;
    ElementOfBinaryTree<ClassType> rightChild;


    public ElementOfBinaryTree(ElementOfBinaryTree<ClassType> elementOfBinaryTree) {
        this.numberOfRepetitions= elementOfBinaryTree.numberOfRepetitions;
        this.inf = elementOfBinaryTree.inf;
        this.leftChild = elementOfBinaryTree.leftChild;
        this.rightChild = elementOfBinaryTree.rightChild;
        this.parent= elementOfBinaryTree.parent;
    }

    public ElementOfBinaryTree(ClassType inf, ElementOfBinaryTree<ClassType> parent) {
        this.parent=parent;
        this.inf = inf;
        this.numberOfRepetitions=1;
        this.leftChild=null;
        this.rightChild=null;
    }

    public ClassType getInf() {
        return this.inf;
    }

    public int getNumberOfRepetitions() {
        return this.numberOfRepetitions;
    }

    public ElementOfBinaryTree<ClassType> getLeftChild() {
        return leftChild;
    }

    public ElementOfBinaryTree<ClassType> getRightChild() {
        return rightChild;
    }
}
