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
        binaryTree.inOrder();
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
        return null;
    }

    @Override
    public int countBook() {
        return binaryTree.totalNode();
    }

    @Override
    public boolean removeBook(String bookCode) {
        return true;
    }
    @Override
    public void printAvailableBook() {
        binaryTree.printAvailable();
    }
    @Override
    public void balancing() {
    }
    @Override
    public Book searchBookbyName(String name) {
         return null;
    }    
}
