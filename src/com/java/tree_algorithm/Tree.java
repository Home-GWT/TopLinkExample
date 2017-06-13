package com.java.tree_algorithm;


import java.util.Arrays;

/**
 * @see http://www.ivan-nikolov.com/ru/article/12/algorithms-and-data-structures-binary-trees
 *
 * Операции над бинарными деревьями:
 * 1. Добавление
 * 2. Удаление
 * 3. Поиск
 * 4. Обход – это дополнительная операция к трем основным, которые мы рассматривали в структурах данных.
 */
public class Tree {
    private Node root;

    public Tree() {
        this.root = null;
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
//        System.out.println( tree.root );

        tree.insert(4);
        tree.insert(6);
        tree.insert(7);
        tree.insert(9);
        tree.insert(10);
        tree.insert(16);
        tree.insert(19);

        System.out.println( tree.root );
        tree.traverseTree(TraverseType.INORDER);

//        Node node = tree.find(5);
//        System.out.println( node.getData() );
    }


    /*
     * Добавление
     */
    public void insert(int data) {
        root = insert(root, data, null);
    }

    private Node insert(Node current, int data, Node parent) {
//        System.out.println("current="+current+"; data="+data+"; parent="+parent+";");
        if (current == null) {
//            System.out.println("1");
            current = new Node();
            current.setData(data);
            current.setLeft(null);
            current.setRight(null);
            current.setParent(parent);
        } else if (data < current.getData()) {
//            System.out.println("2");
            current.setLeft( insert(current.getLeft(), data, current) );
        } else {
//            System.out.println("3");
            current.setRight(insert(current.getRight(), data, current));
        }
//        System.out.println("4");
        return current;
    }

    /*
     * Поиск
     */
    public Node find(int data) {
        return find(root, data);
    }

    private Node find(Node current, int data) {
        if (current == null)
            return null;
        if (current.getData() == data)
            return current;
        return find(data < current.getData() ? current.getLeft() : current.getRight(), data);
    }

    public Node findMin() {
        Node min = root;
        if (min == null) return null;
        while (min.getLeft() != null) {
            min = min.getLeft();
        }
        return min;
    }

    public Node findMin(Node min) {
        if (min == null) return null;
        while (min.getLeft() != null) {
            min = min.getLeft();
        }
        return min;
    }

    /*
     * Удаление
     * ********
     * 1. Удаление элемента без детей – просто освобождаем память.
     * 2. Удаление элемента с одним ребенком – смена указателя родителя указывать директно к ребенку удаляемого элемента и освобождение памяти.
     * 3. Удаление элемента с только одним ребенком и это КОРЕНЬ – перемещение ребенка на место корня и освобождение памяти.
     * 4. Удаление элемента с двумя детьми – это самая сложная операция. Самый подходящий способ исполнения это разменять стоимости удаляемого элемента и максимальную стоимость левого поддерева или минимальную правого поддерева (потому что это сохранит характеристики дерева) и тогда удаляем элемент без или с одним ребенком.
     */
    public void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node startNode, int data) {
        Node element = find(startNode, data);
        if (element == null) return startNode;
        boolean hasParent = element.getParent() != null;
        boolean isLeft = hasParent && element.getData() < element.getParent().getData();
        if (element.getLeft() == null && element.getRight() == null) {
            if (hasParent) {
                if (isLeft) {
                    element.getParent().setLeft(null);
                } else {
                    element.getParent().setRight(null);
                }
            }
        } else if (element.getLeft() != null && element.getRight() == null) {
            if (hasParent) {
                if (isLeft) {
                    element.getParent().setLeft(element.getLeft());
                } else {
                    element.getParent().setRight(element.getLeft());
                }
            } else {
                startNode = element.getLeft();
            }
        } else if (element.getLeft() == null && element.getRight() != null) {
            if (hasParent) {
                if (isLeft) {
                    element.getParent().setLeft(element.getRight());
                } else {
                    element.getParent().setRight(element.getRight());
                }
            } else {
                startNode = element.getRight();
            }
        } else {
            Node rightMin = findMin(element.getRight()); //TODO
            element.setData(rightMin.getData());
            return delete(rightMin, rightMin.getData());
        }
        element = null;
        return startNode;
    }

    /*
     * Обход дерева
     * ************
     * Обход дерева, это процесс обхождения всех узлов и их обрабатывании. Существует три типа обхода бинарного дерева:
     * 1. Inorder – посещение левого поддерева, корня и правого поддерева.
     * 2. Preorder – посещение корня, левого поддерева и правого поддерева.
     * 3. Postorder – посещение левого поддерева, правого поддерева и корня.
     */
    public enum TraverseType {
        INORDER,
        PREORDER,
        POSTORDER;
    }

    public void traverseTree(TraverseType traverseType) {
        traverseTree(root, traverseType);
    }

    private void traverseTree(Node current, TraverseType traverseType) {
        if (current == null)
            return;
        switch (traverseType) {
            case INORDER:
                traverseTree(current.getLeft(), traverseType);
                System.out.println(current.getData()); //processNode(current);
                traverseTree(current.getRight(), traverseType);
                break;
            case PREORDER:
                System.out.println(current.getData()); //processNode(current);
                traverseTree(current.getLeft(), traverseType);
                traverseTree(current.getRight(), traverseType);
                break;
            case POSTORDER:
                traverseTree(current.getLeft(), traverseType);
                traverseTree(current.getRight(), traverseType);
                System.out.println(current.getData()); //processNode(current);
                break;
        }
    }
}
