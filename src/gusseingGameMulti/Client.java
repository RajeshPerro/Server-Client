/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gusseingGameMulti;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;

/**
 *
 * @author rajesh
 */
public class Client {

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 59313;
        byteHandle bh = new byteHandle();
        byte[] message = new byte[4],
                response = new byte[3];

        try {
            System.out.println("Connecting to " + address + " on port " + port);
            Socket socket = new Socket(address, port);
            System.out.println("Just Connected to " + socket.getInetAddress());

            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            //OutputStream socketOutStream = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String ClientInput = "", name = "";
            boolean loop = true;
            while (loop) {
                System.out.println("***--Please Follow the instruction--***\n");
                System.out.println("**Start New Game (press 1) \n **Enter a Number (press 2) \n**Check high score (press 3)\n**End the game (type : bye)\n");
                ClientInput = br.readLine();
                if (ClientInput.equalsIgnoreCase("bye")) {
                    ClientInput = "4";
                } else {
                    int inpNum = Integer.parseInt(ClientInput);
                    message[0] = (byte) inpNum;
                }

                switch (ClientInput) {
                    case "1":
                        System.out.println("Please enter the difficulty level");
                        int level;
                        level = br.read();
                        byte[] TempNumber = bh.toByteArray(level);
                        message[1] = 0;
                        message[2] = TempNumber[0];
                        message[3] = TempNumber[1];
                        outStream.write(message, 0, 4);
                        //socketOutStream.write(message, 0, 4);
                        break;
                    case "2":
                        System.out.println("Guess the Number : ");
                        int gusNum = br.read();
                        byte[] TempNumber2 = bh.toByteArray(gusNum);
                        message[0] = 2;
                        message[1] = 0;
                        message[2] = TempNumber2[0];
                        message[3] = TempNumber2[1];
                        outStream.write(message, 0, 4);
                        inStream.read(response, 0, 3);

                        switch (response[2]) {
                            case 0:
                                System.out.println("your guess is too low! try again.");
                                break;
                            case 1:
                                System.out.println("your guess is too big! try again.");
                                break;
                            case 2:
                                System.out.println("Perfect! \nWhat is your name? \nName : ");
                                //String [] tempStr = new String[4];
                                name = br.readLine();
                                String str = String.join(";", "2", "1", name);
                                ClientInput = str;
                                outStream.writeUTF(ClientInput);
                                outStream.flush();

                                break;
                        }
                        break;

                    case "3":
                        System.out.println("No highScore generated!\n");
                        break;
                    case "4":
                        ClientInput = "bye";
                        outStream.writeUTF(ClientInput);
                        outStream.flush();
                        String serverMessage = inStream.readUTF();
                        System.out.println(" Server Say's: " + serverMessage);
                        outStream.close();
                        socket.close();
                        loop=false;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
