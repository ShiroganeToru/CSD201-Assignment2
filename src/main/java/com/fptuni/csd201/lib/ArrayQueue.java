/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import com.fptuni.csd201.ds.Queue;

/**
 *
 * @author DUNGHUYNH
 */
public class ArrayQueue implements Queue{
    
    public Node[] book;
    public int max;
    public int first, last;
    
    public ArrayQueue(){
        this(10);
    }
    
    public ArrayQueue(int max1){
        max = max1;
        book = new Node[max];
        first = last = -1;
    }
    @Override
    public boolean isEmpty() {
        return (first == -1);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enqueue(Node x) {
        if(last == max - 1 || last == -1){
            book[0] = x; last = 0;
            if(first == -1) first = 0;
        } else book[++last] = x;
    }

    @Override
    public Node dequeue() {
        Node x = book[first];
        if(first == last) first = last = -1;
        else if(first == max - 1) first = 0;
        else first++;
        return x;
    }

    @Override
    public int first() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void traverse() {
        
    }
    
}
