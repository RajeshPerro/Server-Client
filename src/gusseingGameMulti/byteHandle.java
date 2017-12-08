/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusseingGameMulti;

import java.nio.ByteBuffer;

/**
 *
 * @author rajesh
 */
public class byteHandle {

    public  byte[] toByteArray(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    public int fromByteArray(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

}

/* to use this class you just need a main method as like bellow  
public static void main(String[] args) {
        byteHandle bh = new byteHandle();
        int number = 12345;
        byte[] bytes = bh.toByteArray(number);
        for (byte b : bytes) {
            System.out.format("0x%x ", b);
           
            
        }
         int temp = bh.fromByteArray(bytes);
        System.out.println("\nCalling function to convert byte :"+temp);


    }*/