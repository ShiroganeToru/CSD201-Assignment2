/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import com.fptuni.csd201.object.Book;

/**
 *
 * @author DUNGHUYNH
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(Book x) {
        Node newNode = new Node(x);
        if (root == null) {
            root = newNode;
        } else {
            addNode(root, newNode);
        }
    }

    public void addNode(Node node, Node newNode) {
        if (newNode.info.getCode().compareTo(node.info.getCode()) < 0) {
            if (node.left == null) {
                node.left = newNode;
            } else {
                addNode(node.left, newNode);
            }
        } else {
            if (node.right == null) {
                node.right = newNode;
            } else {
                addNode(node.right, newNode);
            }
        }
    }

    public void inOrder() {
        inOrderTraversal(root);
    }

    public void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.println(node.info);
        inOrderTraversal(node.right);
    }

    public void breadthFirst() {
        if (root == null) {
            return;
        }

        ArrayQueue queue = new ArrayQueue();
        queue.enqueue(root);
        Node p;
        while (!queue.isEmpty()) {
            p = (Node) queue.dequeue();
            System.out.println(p.info);
            if (p.left != null) {
                queue.enqueue(p.left);
            }
            if (p.right != null) {
                queue.enqueue(p.right);
            }

        }
    }
    
    public int totalNode(){
        return countNode(root);
    }
    
    public int countNode(Node node){
        if(node == null) return 0;
        
        int countLeft = countNode(node.left);
        int countRight = countNode(node.right);
        
        return countLeft + countRight + 1;
    }
    
    public void printAvailable(){
        if (root == null) {
            return;
        }

        ArrayQueue queue = new ArrayQueue();
        queue.enqueue(root);
        Node p;
        while (!queue.isEmpty()) {
            p = (Node) queue.dequeue();
            if(p.info.getLended() <  p.info.getQuantity())
                System.out.println(p.info);
            if (p.left != null) {
                queue.enqueue(p.left);
            }
            if (p.right != null) {
                queue.enqueue(p.right);
            }

        }
    }
}
