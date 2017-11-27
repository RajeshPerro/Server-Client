/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasockettest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajesh
 */
public class clientSide 
{
    
    public static void main(String [] args)
    {
        String address="127.0.0.1";
        int port=6606;
        int number,getNum;
        String temp,str;
        Scanner sc  = new Scanner(System.in);
        
        try {
            System.out.println("Connecting to " + address + " on port " + port);
            Socket client = new Socket(address, port);
            System.out.println("Just Connected to "+client.getInetAddress());
           
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            
            InputStream inputServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inputServer);
            //Sending to server...
            out.writeUTF("Hello from "+client.getLocalSocketAddress());
            System.out.println("Hey, Eneter your name to play");
            str = sc.nextLine();
            
            System.out.println("Thanks! "+ str +" Now Enter any number : ");
            number = sc.nextInt();
            temp = ""+number;
            System.out.println("Client you enter : "+number);
            
            
            out.writeUTF(temp);
            
            String question;
            
            String xx;
            xx = in.readUTF();
            if(isInteger(xx) == true)
            {
                getNum = Integer.parseInt(xx);
                System.out.println("Return From Server : "+getNum);
                question = in.readUTF();
                if(getNum == 1)
                {
                    
                   // out.writeUTF(str);
                    
                    System.out.println("Server says : "+question);
                    out.writeUTF("Hello My name is  "+str+"\n");
                    
                }
                
            }
            else
            {
                
                 System.out.println("Server says "+in.readUTF());
            }
            
           
            
            //client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(clientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
     public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
}
