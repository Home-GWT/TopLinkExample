package com.java;

import javax.swing.tree.*;
import java.util.*;

/**
 * Обход бинарного дерева по уровням
 * *********************************
 *  @see http://blog.cybdev.org/obhod-binarnogo-dereva-po-urovnyam
 ** @see http://algolist.manual.ru/ds/walk.php
 ** @see https://sohabr.net/habr/post/232669
 *
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        if (root == null) {
//            return result;
//        }
//        Queue<TreeNode> tmpQueue = new LinkedList<TreeNode>();
//        Stack<Queue<TreeNode>> stack = new Stack<Queue<TreeNode>>();
//        TreeNode tmp = root;
//        tmpQueue.add(tmp);
//        stack.push(tmpQueue);
//        while (!stack.isEmpty()) {
//            tmpQueue = stack.pop();
//            Queue<TreeNode> newQueue = new LinkedList<TreeNode>();
//            List<Integer> list = new ArrayList<Integer>();
//            while (!tmpQueue.isEmpty()) {
//                tmp = tmpQueue.poll();
//                list.add(tmp.val);
//                if (tmp.left != null) {
//                    newQueue.add(tmp.left);
//                }
//                if (tmp.right != null) {
//                    newQueue.add(tmp.right);
//                }
//            }
//            if (!newQueue.isEmpty()) {
//                stack.push(newQueue);
//            }
//            result.add(list);
//        }
//        tmp = null;
//        tmpQueue = null;
//        return result;
//    }

}
