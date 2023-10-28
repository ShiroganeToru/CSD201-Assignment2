/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.csd201.bookservice;

import com.fptuni.csd201.lib.BinaryTree;
import com.fptuni.csd201.object.Book;

/**
 *
 * @author DUNGHUYNH
 */
public class BookServiceImpl implements BookService {
    private BinaryTree binaryTree;
    
    public BookServiceImpl(){
        binaryTree = new BinaryTree();
    }
    
    @Override
    public boolean addBook(Book book) {
        binaryTree.insert(book);
        return true;
    }

    @Override
    public void printBook(Book book) {
        binaryTree.printBook(book);
    }

    @Override
    public void showBook(int method) {
        switch(method){
            case 1:
                binaryTree.inOrder();
                break;
            case 2:
                binaryTree.breadthFirst();
                break;
        }
    }

    @Override
    public Book searchBookbyCode(String bookCode) {
        return binaryTree.searhByBookCode(bookCode);
    }

    @Override
    public int countBook() {
        return binaryTree.totalNode();
    }

    @Override
    public boolean removeBook(String bookCode) {
        binaryTree.removeByBookCode(bookCode);
        return true;
    }
    @Override
    public void printAvailableBook() {
        binaryTree.printAvailable();
    }
    @Override
    public void balancing() {
        binaryTree.balanceTree();
    }
    @Override
    public Book searchBookbyName(String name) {
        return binaryTree.search(name);
    }    
}
