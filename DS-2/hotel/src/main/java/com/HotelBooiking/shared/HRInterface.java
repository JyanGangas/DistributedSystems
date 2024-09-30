/****************************************************
 DESCRIPTION:	RMI Hotel
 AUTHOR:	John Gangas (AM: 19390038)
 CLASS:		ΧΠ
 DATE: 		
 ***************************************************/
package com.HotelBooking.shared;

import java.rmi.RemoteException;
// Interface code for RMI-server
public interface HRInterface extends java.rmi.Remote {
    // Creation of the needed calls.
    public String list() throws RemoteException;

    public String book(String type, int number, String name) throws RemoteException;

    public String guests() throws RemoteException;

    public String cancel(String type, int number, String name) throws RemoteException;

}