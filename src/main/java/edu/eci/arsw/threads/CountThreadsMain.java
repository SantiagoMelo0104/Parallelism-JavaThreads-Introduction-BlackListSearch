/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread Thread1 = new CountThread(0,99);
        CountThread Thread2 = new CountThread(99,199);
        CountThread Thread3 = new CountThread(200,299);
        Thread1.run();
        Thread2.run();
        Thread3.run();


    }
    
}
