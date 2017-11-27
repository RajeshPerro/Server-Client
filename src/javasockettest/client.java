/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasockettest;


import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.exit;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author rajesh
 */
public class client {

    public static void main(String[] args) throws IOException {
        int number;
        String temp;
        String UserName, question;
        String IPaddress = "127.0.0.1";
        int Port = 6666;
        Scanner sc = new Scanner(System.in);
        Socket soc = new Socket(IPaddress, Port);
        Scanner serverIn = new Scanner(soc.getInputStream());
        PrintStream pout = new PrintStream(soc.getOutputStream());
        pout.println("Hello Server!");

        System.out.println("Enter any nymber : ");
        while (sc.hasNextInt()) {
            number = sc.nextInt();
            System.out.println("Number enter by client : " + number);
            
            //Sending data to Server....actually sending to socket
            pout.println(number);

            //Accepting from Server / reading Socket....
           
            temp = serverIn.nextLine();
            //Integer.parseInt(temp);
           // question = serverIn.nextLine();
            if (Integer.parseInt(temp) == 2) {
                System.out.println("Your guess is too high!");
                System.out.println("Return from Server : "+temp);
            } else if (Integer.parseInt(temp) == 0) {
                System.out.println("Your guess is too low!");
                System.out.println("Return from Server : "+temp);
            } else if(Integer.parseInt(temp) == 1){
                System.out.println("Seems OKay!");
                System.out.println("Return from Server : "+temp);
            }
            else
            {
                 UserName = sc.nextLine();
                pout.println(UserName);
            }
            System.out.println("Enter any nymber : ");
            if (number == -1) {
                pout.println(number);
                serverIn.close();
                pout.close();
                exit(0);
            }
           System.out.println("Return from Server : "+temp);
        }

    }
}
