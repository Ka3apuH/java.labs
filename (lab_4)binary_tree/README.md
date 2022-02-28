`sudo apt install maven` для начала

###реализуемый класс бинарного дерева: 
[`BinaryTree`](src/main/java/BinaryTree/BinaryTree.java) находится в 
<pre> $PROJECT_DIR$/src/main/java/BinaryTree </pre>

тест данного класса : [`BinaryTreeTest`](src/test/java/BinaryTreeTest/BinaryTreeTest.java) находится в
<pre> $PROJECT_DIR$/src/test/java/BinaryTreeTest </pre>

класс описывающий элемент бинарного дерева:
[`ElementOfBinaryTree`](src/main/java/BinaryTree/ElementOfBinaryTree.java) находится в
<pre> $PROJECT_DIR$/src/main/java/BinaryTree </pre>

###исключение вызываемые из класса _BinaryTree_:

[`NotFoundElementOfTreeException`](src/main/java/ExceptionsOfBinaryTree/NotFoundElementOfTreeException.java) находится в
<pre> $PROJECT_DIR$/src/main/java/ExceptionsOfBinaryTree </pre>

тест данного класса : [`NotFoundElementOfTreeExceptionTest`](src/test/java/ExceptionsOfBinaryTree/NotFoundElementOfTreeExceptionTest.java)находится в
<pre> $PROJECT_DIR$/src/test/java/ExceptionsOfBinaryTree </pre>

###для компиляции:

<pre>mvn install package</pre>

###запустить тестирование:
<pre>mvn test</pre>
