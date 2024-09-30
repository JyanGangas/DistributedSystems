/****************************************************
 DESCRIPTION:	RMI Hotel
 AUTHOR:	John Gangas (AM: 19390038)
 CLASS:		ΧΠ
 DATE: 		
 ***************************************************/
package com.HotelBooking.HotelBooking.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.HotelBooking.shared.HRInterface;

public class HRServer {
    public static void main(String[] args) throws RemoteException{
        try {
            HRInterface server = new HRImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/HRImpl";
            Naming.rebind(url, server);  //create rmi server
            System.out.println("Server is running.");
        } catch (MalformedURLException | UnknownHostException | RemoteException e){
        System.out.println("Trouble: " + e );}
    }
}
