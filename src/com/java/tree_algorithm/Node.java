package com.java.tree_algorithm;


/**
 * @see http://www.ivan-nikolov.com/ru/article/12/algorithms-and-data-structures-binary-trees
 *
 * фрагмент кода показывает структуру бинарного дерева
 * ***************************************************
 * 1. Родитель – это прямой предшественник узла дерева.
 * 2. Ребенок – прямой наследник узла дерева.
 * 3. Предшественник
 * 4. Наследник
 *
 * Каждый узел имеет по два ребенка.
 * Стоимость левого ребенка меньше стоимости родителя.
 * Стоимость правого ребенка больше стоимости родителя.
 */
public class Node {
    private int data;
    private Node left;
    private Node right;
    private Node parent;

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
