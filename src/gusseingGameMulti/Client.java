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
import java.net.Socket;
import java.nio.charset.Charset;

/**
 *
 * @author rajesh
 */
public class Client {

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 6060;
        byteHandle bh = new byteHandle();
        byte[] message = new byte[4],
                response = new byte[4];

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
                System.out.println("********--Please Follow the instruction--********\n");
                System.out.println("**Start New Game (press 1)\n **Enter a Number (press 2)\n**Check high score (press 3)\n**End the game (type : bye)\n");
                System.out.println("*********************************");
                ClientInput = br.readLine();
                if (ClientInput.equalsIgnoreCase("bye")) {
                    ClientInput = "4";
                }  
                    int inpNum = Integer.parseInt(ClientInput);
                    message[0] = (byte) inpNum;
                
                //System.out.println("Client input value : "+ClientInput+"\n");
                switch (ClientInput) {
                    case "1":
                        System.out.println("Please enter the difficulty level (1 to 3)");
                        System.out.println("************Note**************");
                        System.out.println("\nif you chooose Level-1 : You have to guess num 1 - 20\n if you chooose Level-2 : You have to guess num 1 - 200\n if you chooose Level-3 : You have to guess num 1 - 2000");
                        System.out.println("*********************************");
                        String inputLevel;
                        inputLevel = br.readLine();
                        int level = Integer.parseInt(inputLevel);
                        message[1] = 0;
                        message[2] = 0;
                        message[3] = (byte) level;
                        outStream.write(message, 0, 4);
                        inStream.read(response, 0, 3);

                        System.out.println("\nMessage array : " + "{0} " + message[0] + " {1} " + message[1] + " {2} " + message[2] + " {3} " + message[3]);

                        System.out.println("\nResponse : " + "{0} " + response[0] + " {1} " + response[1] + " {2} " + response[2]);
                        //if client want to start a game without finishing the running one..
                        Byte b = new Byte(response[2]);
                        int lastNumOfResponse = b.intValue();
                        if (lastNumOfResponse == 1) {
                            System.out.println("\nYou are already in Game.!!\nPlease finish the runing one first.");
                        }
                        break;
                    case "2":
                        System.out.println("\nGuess the Number : ");
                        String gusInput = br.readLine();
                        byte[] GusInput = gusInput.getBytes();

                        message[1] = 0;
                        message[2] = (byte) GusInput.length;
                        outStream.write(message, 0, 3);
                        //Sending the user's guess number to server.... :)
                        outStream.write(GusInput);
//                        for(int i =0;i<3;i++)
//                        System.out.println("GusInput from user : "+GusInput[i]);

                        inStream.read(response, 0, 3);
                        System.out.println("{0} " + response[0] + " {1} " + response[1] + " {2} " + response[2]);
                        switch (response[2]) {
                            case 0:
                                System.out.println("\nyour guess is too big! try again.\n");

                                break;
                            case 1:
                                System.out.println("\nyour guess is too low! try again.\n");
                                break;
                            case 2:
                                System.out.println("\nPerfect! \nWhat is your name? \nName : ");
                                //String [] tempStr = new String[4];
                                name = br.readLine();
                                //String str = String.join(";", "2", "1", name);
                                byte[] NameByte = name.getBytes();
                                message[1] = 1;
                                message[2] = (byte) NameByte.length;

                                outStream.write(message, 0, 3);
                                outStream.write(NameByte);

                                inStream.read(response, 0, 3);
                                System.out.println("{0} " + response[0] + " {1} " + response[1] + " {2} " + response[2]);
                                Byte bNameRes = new Byte(response[1]);
                                int afterGetName = bNameRes.intValue();
                                if (afterGetName == 1) {
                                    System.out.println(" Server Say's: Thanks!\n ");
                                }
                                outStream.flush();
                                break;
                                /*The Java byte type is an 8 bit signed integral type with values in the range -128 to +127.
                                The literal 0xff represents +255 which is outside of that range.*/     
                            case 127:
                                System.out.println("\nSorry!! you didn't start the game.!\n");
                                break;
                        }
                        break;

                    case "3":
                        message[1] = 0;
                        message[2] = 0;
                        outStream.write(message, 0, 3);
                        inStream.read(response, 0, 3);
                        System.out.println("{0} " + response[0] + " {1} " + response[1] + " {2} " + response[2]);
                        Byte bHighSc = new Byte(response[2]);
                        int HighScore = bHighSc.intValue();
                        if(HighScore == 0)
                        {
                          System.out.println("\nNo highScore generated!\n");
                        }
                        else{
                            System.out.println("\nyour score is : "+HighScore +" Bravo!!\n");
                        }
                        
                        break;
                    case "4":
                        message[1] = 1;
                        message[2] = 0;
                        System.out.println("\nMessage array : " + "{0} " + message[0] + " {1} " + message[1] + " {2} " + message[2]);
                        outStream.write(message, 0, 3);
                        outStream.flush();
                        inStream.read(response, 0, 3);
                        System.out.println("{0} " + response[0] + " {1} " + response[1] + " {2} " + response[2]);
                        if (response[2] == 1) {
                            System.out.println(" Server Say's: GoodBye!\n ");
                            outStream.close();
                            socket.close();
                            loop = false;
                        }
                   

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
