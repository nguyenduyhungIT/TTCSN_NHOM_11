/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication18;

import java.util.Scanner;

/**
 *
 * @author VuDung
 */
public class onClass1 {
    public static boolean check(int n){
        if(n<2) return false;
        else {
            for(int i=2;i<=n/2;i++){
            if(n%i==0) return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        
        Scanner sc  = new Scanner(System.in);
        
        int n= (int) (Math.random() * 1000);
        
        if(check(n))
        {System.out.println(n+" la so nguyen to day nhe");
        }
        
        else
        {System.out.println(n+" khong phair so nguyen to");}
    }

    
    
}
