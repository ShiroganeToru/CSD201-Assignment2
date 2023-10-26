package com.fptuni.csd201.lib;

import com.fptuni.csd201.object.Book;

public class Node {
    Book info;
    Node left = null, right = null;
    
    Node(){    
    }
    
    Node(Book info, Node left, Node right){
        this.info = info;
        this.left = left;
        this.right = right;
    }
    
    Node(Book info){
        this(info, null, null);
    }
    
    public Book getInfo(){
        return info;
    }
    
    public void setInfo(Book info){
        this.info = info;
    }
    
    public Node getLeft(){
        return left;
    }
    
    public void setLeft(Node left){
        this.left = left;
    }
    
    public Node getRight(){
        return right;
    }
    
    public void setRight(Node right){
        this.right = right;
    }
}
