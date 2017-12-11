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

 //to use this class you just need a main method as like bellow  
//public static void main(String[] args) {
//        byteHandle bh = new byteHandle();
//        int number = 2;
//        byte[] bytes = bh.toByteArray(number);
//        
//        for (byte b : bytes) {
//            System.out.format("Byte print : 0x%x ", b);
//           
//            
//        }
//        System.out.println("Length : "+bytes.length);
//        System.out.println("\nBefore convert : "+bytes[0]);
//        int temp;
//        Byte  bt = new Byte(bytes[0]);
//        temp = bt.intValue();
//        if(temp == 1)
//        {
//            System.out.println("yesss\n");
//        }
//        System.out.println("\nBytes : "+bytes[2]+"\nCalling function to convert byte :"+temp);
//
//
//    }
//
//}