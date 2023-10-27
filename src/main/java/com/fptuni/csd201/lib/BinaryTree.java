/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import com.fptuni.csd201.object.Book;
import java.util.ArrayList;
import java.util.List;

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

        updateHeight(node);
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (node.left.info.getCode().compareTo(newNode.info.getCode()) > 0) {
                rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                rotateRight(node);
            }
        }

        if (balanceFactor < -1) {
            if (node.right.info.getCode().compareTo(newNode.info.getCode()) > 0) {
                rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                rotateLeft(node);
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

    public int totalNode() {
        return countNode(root);
    }

    public int countNode(Node node) {
        if (node == null) {
            return 0;
        }

        int countLeft = countNode(node.left);
        int countRight = countNode(node.right);

        return countLeft + countRight + 1;
    }

    public void printAvailable() {
        if (root == null) {
            return;
        }

        ArrayQueue queue = new ArrayQueue();
        queue.enqueue(root);
        Node p;
        while (!queue.isEmpty()) {
            p = (Node) queue.dequeue();
            if (p.info.getLended() < p.info.getQuantity()) {
                System.out.println(p.info);
            }
            if (p.left != null) {
                queue.enqueue(p.left);
            }
            if (p.right != null) {
                queue.enqueue(p.right);
            }

        }
    }

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    public Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    public void balanceTree() {
        if (root == null) {
            return;
        }

        Node newRoot = null;
        List<Book> lib = new ArrayList<>();
        storeBook(root, lib);

        newRoot = constructTree(lib, 0, lib.size() - 1);

        root = newRoot;
    }

    public void storeBook(Node node, List<Book> lib) {
        if (node == null) {
            return;
        }

        storeBook(node.left, lib);
        lib.add(node.info);
        storeBook(node.right, lib);
    }

    public Node constructTree(List<Book> lib, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node newNode = new Node(lib.get(mid));

        newNode.left = constructTree(lib, start, mid - 1);
        newNode.right = constructTree(lib, mid + 1, end);

        updateHeight(newNode);

        return newNode;
    }
}
