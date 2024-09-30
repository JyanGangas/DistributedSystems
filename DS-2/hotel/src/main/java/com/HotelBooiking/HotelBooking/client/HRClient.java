
/****************************************************
 DESCRIPTION:	RMI Hotel
 AUTHOR:	John Gangas (AM: 19390038)
 CLASS:		ΧΠ
 DATE: 		
 ***************************************************/
package com.HotelBooking.HotelBooking.client;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.HotelBooking.shared.HRInterface;
    
public class HRClient extends UnicastRemoteObject   {
    protected HRClient() throws RemoteException{
    }

    public  static void main(String[] args){
        String error = "INVALID NUMBER OF ARGUMENTS!";
        try{
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/HRImpl";
            Remote remoteObject = Naming.lookup(url);
            HRInterface Rserver = (HRInterface) remoteObject;

            System.out.println("Welcome to our Hotel Booking System.");

        if(args.length < 1){
            printOptions();
        } else {
            switch (args[0]) {
                case "list" -> {
                    if (args.length == 1) {
                        System.out.println(Rserver.list());
                    } else {
                        System.err.println(error);
                        System.exit(1);
                    }
                    }
                case "book" -> {
                    if (args.length == 4) {
                        String answer = null;
                        //Integer.valueOf(args[2]),
                        answer = Rserver.book(args[1], Integer.parseInt(args[2]), args[3]);
                        System.out.println(answer);
                        if (answer.contains("available")) {
                            try (Scanner myObj = new Scanner(System.in)) {
                                String answerscan = myObj.nextLine();
                                if (answerscan.equalsIgnoreCase("Y")) {
                                }
                            }
                        }
                    } else {
                        System.err.println(error);
                        System.exit(1);
                    }
                    }
                case "guests" -> {
                    if (args.length == 1) {
                        System.out.print(Rserver.guests());
                    } else {
                        System.err.println(error);
                        System.exit(1);
                    }
                    }
                case "cancel" -> {
                    if (args.length == 4) {
                        System.out.print(Rserver.cancel(args[1], Integer.parseInt(args[2]), args[3]));
                    } else {
                        System.err.println(error);
                        System.exit(1);
                    }
                    }
                default -> printOptions();
            }
        }
        }catch (NumberFormatException | MalformedURLException | UnknownHostException | NotBoundException | RemoteException e){
            System.err.println("Client exception: " + e.toString());

        }
    }
    public void  AvailCha(String msg) throws RemoteException{
        System.out.println(msg);
    }

    public static void printOptions() { //usage
        System.out.println("""
                           ~Menu~
                           1. java HRClient list: Display all available rooms with information on them
                           2. java HRClient book:<type> <number> <name>: Makes a reservation of <numbers> rooms that are of type <type> in the name of <name>
                           3. java HRClient guests: List of clients and their bookings 
                           4. java HRClient cancel: <type> <number> <name>: Cancels the booking of <name> that are type <type> and add up to <number. Also returns a list of available rooms>
                           """);
    }

}
