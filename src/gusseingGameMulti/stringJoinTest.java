/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusseingGameMulti;

/**
 *
 * @author rajesh
 */
public class stringJoinTest 
{
    public static void main(String [] args)
    {
        String str= String.join(";", "Raejsh Ghosh","yes");
        System.out.println(str);
        String[] words=str.split("\\;");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
