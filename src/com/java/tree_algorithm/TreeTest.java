package com.java.tree_algorithm;


public class TreeTest {

    class MyNode {
        int data;
        MyNode left;
        MyNode right;
        MyNode parent;
    }

    MyNode root;


    public static void main(String[] args) {
        TreeTest test = new TreeTest();

        test.root = test.insert(test.root, 2, null);
        test.root = test.insert(test.root, 1, null);
        test.root = test.insert(test.root, 3, null);

        test.traverse(test.root);
    }


    MyNode insert(MyNode current, int data, MyNode parent){
        if(current == null){
            current = new MyNode();
            current.left = null;
            current.right = null;
            current.parent = parent;
            current.data = data;
        } else if(data < current.data) {
            current.left = insert(current.left, data, current);
        } else {
            current.right = insert(current.right, data, current);
        }

        return current;
    }

    void traverse (MyNode current){
        if(current == null)
            return;

        traverse(current.left);
        traverse(current.right);
    }
}
