/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasockettest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;

/**
 *
 * @author rajesh
 */
public class serverSide extends Thread {

    private ServerSocket serverSocket;
    static int num, randomGenNum;
    String temp;
    String nameofUser;
    Random random = new Random();

    public serverSide(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(15000);
    }

    public void run() {

        try {
            System.out.println("Waiting for client on port "
                    + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            System.out.println(in.readUTF());
            temp = in.readUTF();

            if (isInteger(temp) == true) {

                num = Integer.parseInt(temp);
                //System.out.println("Double of the number read from client " + num*2);
                randomGenNum = ranDomGenerate(num);

                System.out.println("random number : " + randomGenNum);
                while (true) {
                    if (randomGenNum > num) {
                        temp = "2";
                        out.writeUTF(temp);

                    } else if (randomGenNum == 1 || randomGenNum == 0) {
                        temp = "0";
                        out.writeUTF(temp);

                    } else {
                        temp = "1";
                        out.writeUTF(temp);
                        out.writeUTF("What is your Name ?");
                        break;

                    }
                   
                }

            } else {
                System.out.println("he he he!");
                //nameofUser = in.readUTF();
                System.out.println("User Name is : " + temp);
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                    + "\nGoodbye!");
            //server.close();
            }

//                    nameofUser = in.readUTF();
//                    System.out.println("User Name is : " + nameofUser);
            

        } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            //break;
        } catch (IOException e) {
            e.printStackTrace();
            // break;
        }

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int ranDomGenerate(int x) 
    {
        int ranNum = random.nextInt(num * 2) + 1;
        return ranNum;
    }

    public static void main(String[] args) {
        int port = 6606;
        try {
            Thread t = new serverSide(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
