/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.interfaces;
import java.util.ArrayList;
/**
 *
 * @author Mohamed
 * @param <T>
 */
public interface IService <T>{
    public void add(T entity);
    public ArrayList<T> getUserById(int ID)  ;  
    public ArrayList<T> getAll();
}

