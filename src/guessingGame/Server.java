/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessingGame;

import java.net.*;
import java.io.*;
import java.util.Random;

/**
 *
 * @author rajesh
 */
public class Server {

    public static void main(String[] args) throws Exception {
        int port = 59313;
        try {
            Random random = new Random();
            ServerSocket server = new ServerSocket(port);
            System.out.println("Waiting for client on port "
                    + server.getLocalPort() + "...");
            Socket serverClient = server.accept();
            System.out.println("Just connected to " + serverClient.getRemoteSocketAddress());

            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int randomGenNum = 0, temp = 0;
            boolean flag = true;
            String clientMessage = "", serverMessage = "";

            while (!clientMessage.equals("bye")) {

                clientMessage = inStream.readUTF();
                if (clientMessage.charAt(0) == '@') {
                    //System.out.println("Client Name: " + clientMessage);
                    //String[] Name = clientMessage.split("@");
                    System.out.println(" Client Name is : " + clientMessage.substring(1));
                    serverMessage = "Thanks!";
                    outStream.writeUTF(serverMessage);
                    System.out.println("SerMsg : " + serverMessage);
                    outStream.flush();
                } else {
                    System.out.println(" Client Say's : " + clientMessage);

                    if (isInteger(clientMessage) == true) {
                        System.out.println("yeah");
                        temp = Integer.parseInt(clientMessage);
                        if (flag) {
                            randomGenNum = random.nextInt(temp * 2) + 1;
                            flag = false;
                        }
                        if (randomGenNum > temp) {
                            serverMessage = "2";
                        } else if (randomGenNum < (temp / 2)) {
                            serverMessage = "0";
                        } else if (randomGenNum == temp) {
                            serverMessage = "1";
                            flag = true;
                        }
                        System.out.println("Random Number : " + randomGenNum);
                        System.out.println("SerMsg : " + serverMessage);
                        outStream.writeUTF(serverMessage);
                        outStream.flush();
                    } else {
                        serverMessage = "good bye!";
                        outStream.writeUTF(serverMessage);
                        outStream.flush();

                    }
                }

            }

            inStream.close();
            outStream.close();
            serverClient.close();
            server.close();
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
