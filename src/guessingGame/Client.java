/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessingGame;

import java.net.*;
import java.io.*;

/**
 *
 * @author rajesh
 */
public class Client {

    public static void main(String[] args) throws Exception {
        String address = "127.0.0.1";
        int port = 59313;
        boolean flag = true;
        try {
            
            System.out.println("Connecting to " + address + " on port " + port);
            Socket socket = new Socket(address, port);
            System.out.println("Just Connected to " + socket.getInetAddress());
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage = "", serverMessage = "", name="";

            while (!clientMessage.equals("bye")) {
                if(flag){
                System.out.println("Enter a number to play or type bye to finish the game.");
                clientMessage = br.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                }
                
                serverMessage = inStream.readUTF();
                
                if (isInteger(serverMessage) == true) {
                    int temp = Integer.parseInt(serverMessage);
                    if (temp == 2) {
                        System.out.println("your guess is too low! try again.");
                    } else if (temp == 0) {
                        System.out.println("your guess is too big! try again.");
                    } else {
                        System.out.println("Perfect! \nWhat is your name? \nName : ");
                        clientMessage = br.readLine();
                        name = clientMessage;
                        clientMessage = "@" + name;
                        outStream.writeUTF(clientMessage);
                        flag=false;
                    }
                }
                else
                {
                    System.out.println(" Server Say's: " + serverMessage);
                    flag = true;
                }
            }
            //clientMessage = "bye";
            outStream.writeUTF(clientMessage);
            outStream.flush();

            outStream.close();
            outStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
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
