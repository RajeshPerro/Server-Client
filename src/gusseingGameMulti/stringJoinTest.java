/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusseingGameMulti;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 *
 * @author rajesh
 */
public class stringJoinTest 
{
    public static void main(String [] args) throws UnsupportedEncodingException
    {
        String str= String.join(";", "Raejsh Ghosh","yes");
        String str2 = "bye";
        System.out.println(str);
        String[] words=str.split("\\;");
        for (String word : words) {
            System.out.println(word);
        }
        byte [] name = str2.getBytes(Charset.forName("UTF-8"));
        for (byte b : name) {
            System.out.print(b);
        }
        System.out.println("\nLen : "+name.length+" name [0]"+name[0]);
        String text = new String(name, "UTF-8");
        System.out.println("Get back : "+text);
    }
}
