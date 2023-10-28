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
    
    //1. Add book
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
    
    public Node checkBook(Book book){
        return checkBook(root,book);
    }
    
    public Node checkBook(Node node, Book book){
        if(node == null) return null;
        
        if(book.getCode().compareTo(node.info.getCode()) < 0){
            return checkBook(node.left,book);
        } else if(book.getCode().compareTo(node.info.getCode()) > 0){
            return checkBook(node.right,book);
        } else {
            return node;
        }
    }
    
    public void printBook(Book book) {
        if (book != null) {
            System.out.println(book);
        }
    }

    //2. In-order traversal
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

    //3. Breadth-first Traversal
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

    //4. Search book by code
    public Book searhByBookCode(String bookCode) {
        return searchByBookCode(root, bookCode);
    }

    public Book searchByBookCode(Node node, String bookCode) {
        if (node == null) {
            return null;
        }

        if (node.info.getCode().equals(bookCode)) {
            return node.info;
        }

        if (searchByBookCode(node.left, bookCode) != null) {
            return searchByBookCode(node.left, bookCode);
        }

        if (searchByBookCode(node.right, bookCode) != null) {
            return searchByBookCode(node.right, bookCode);
        }

        return null;
    }

    //5. Count book
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

    //6. Remove book
    public void removeByBookCode(String bookCode) {
        root = removeByBookCode(root, bookCode);
    }

    public Node removeByBookCode(Node node, String bookCode) {
        if (node == null) {
            return null;
        }

        if (bookCode.compareTo(node.info.getCode()) < 0) {
            node.left = removeByBookCode(node.left, bookCode);
        } else if (bookCode.compareTo(node.info.getCode()) > 0) {
            node.right = removeByBookCode(node.right, bookCode);
        } else {
            //Case 1: Node with only children
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            //Case 2: Node with two children
            Node successor = findMin(node.right);

            node.info = successor.info;
            node.right = removeByBookCode(node.right, successor.info.getCode());
        }
        return node;
    }

    public Node findMin(Node node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    //7. Print available book
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

    //8. Balance tree
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
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

    //9. Search book by name
    public Book search(String name) {
        return search(root, name);
    }

    public Book search(Node node, String name) {
        if (node == null) {
            return null;
        }

        if (name.equals(node.info.getName())) {
            return node.info;
        }

        if (search(node.left, name) != null) {
            return search(node.left, name);
        }

        if (search(node.right, name) != null) {
            return search(node.right, name);
        }

        return null;
    }
}
