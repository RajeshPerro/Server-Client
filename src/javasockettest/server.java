/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasockettest;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rajesh
 */
public class server {

    public static void main(String[] args) throws IOException {
        int port = 6666;
        int num, temp, send = 0;
        String str;
        ServerSocket ssc = new ServerSocket(port);
        Socket serversock = ssc.accept();
        Scanner sc = new Scanner(serversock.getInputStream());
        // DataInputStream din = new DataInputStream(serversock.getInputStream());
        PrintStream pout = new PrintStream(serversock.getOutputStream());
        Random random = new Random();
        str = sc.nextLine();
        System.out.println(str);
        while (sc.hasNextInt()) {
            num = sc.nextInt();
            System.out.println(num);
            temp = random.nextInt(num * 2) + 1;

            if (temp > num) {
                send = 2;
                pout.println(send);
            } else if (temp == 1) {
                send = 0;
                pout.println(send);
            } else 
            {
                send = 1;
                pout.println("What is your name?");
                pout.println(send);
               
            }
            System.out.print(send);
            if (num == -1) {
//                sc.close();
//                pout.close();
                exit(0);

            }

        }
        

    }

}

 
