/****************************************************
 DESCRIPTION:	RMI Hotel
 AUTHOR:	John Gangas (AM: 19390038)
 CLASS:		ΧΠ
 DATE: 		
 ***************************************************/
package com.HotelBooking.HotelBooking.server;


import java.util.ArrayList;
import java.util.List;

import com.HotelBooking.shared.HRInterface;
// Code to implement the interface.
public class HRImpl extends java.rmi.server.UnicastRemoteObject implements HRInterface {
    // We crate a list that will store every guest that has made a reservation in our hotel.
    private final List<Customer> custList = new ArrayList<>();

    // Creation of "Hotel".
    Hotel MyHotel = new Hotel();

    public HRImpl()
            throws java.rmi.RemoteException {
        super();
    }
    // Prints a list of available room at the moment.
    @Override
    public String list()throws java.rmi.RemoteException{
        String out = null;

        out = "For the rooms that belong in category A, there are " + MyHotel.getQuant("A") + " rooms available with a price of " + MyHotel.getprice("A") + " for each one.\n";
        out = out + "For the rooms that belong in category B, there are " + MyHotel.getQuant("B") + " rooms available with a price of " + MyHotel.getprice("B") + " for each one.\n";
        out = out + "For the rooms that belong in category C, there are " + MyHotel.getQuant("C") + " rooms available with a price of " + MyHotel.getprice("C") + " for each one.\n";
        out = out + "For the rooms that belong in category D, there are " + MyHotel.getQuant("D") + " rooms available with a price of " + MyHotel.getprice("D") + " for each one.\n";
        out = out + "For the rooms that belong in category E, there are " + MyHotel.getQuant("E") + " rooms available with a price of " + MyHotel.getprice("E") + " for each one.\n";
        return out;
    }

    // This one return the results of an attempt to book by a customer. There is the case that it was use properly and then the program checks if there are enough available rooms.
    @Override
    public String book(String type, int number, String name)throws java.rmi.RemoteException{
        // Original message of res is an error message.
        String res = "ERROR COMPLETING BOOKING";
        int bill=0;
        if(MyHotel.book(type, number) ){
            // Calculates the Bill guest has to pay.
            bill = number * MyHotel.getprice(type);
            MyHotel.updateAvail(type, number);
            res = "BOOKING SUCCESFULL, YOUR BILL IS: "+bill+" €";
            Customer MyCustomer = new Customer(name, type, number);
            custList.add(MyCustomer);
        } else if (MyHotel.getQuant(type) > 0){
            res = "There arent enough available rooms. Available rooms at this time are: "+MyHotel.getQuant(type);
        } else {
            res = "There are no rooms available, at this time.\nWOULD YOU LIKE TO GET NOTIFIED WHEN THERE MORE AVAILABLE ROOMS?[Y/N]";
        }
        return res;
    }
    // Returns the list of guests that have made a reservation.
    @Override
    public String guests() throws java.rmi.RemoteException{
        String out = null;
        // Variable to store the size of guestlist.
        int guest = custList.size();
        out = "There are "+guest+" People that have booked a room in our hotel.\n";
        System.out.println(out);
        // For loop that print the guests and their bookings.
        for(Customer d: custList){
            out = out + d.getName()+" has "+d.getRoomCount()+" rooms, category: "+d.getRoomType()+".\n";
            System.out.println(d.getName()+" has "+d.getRoomCount()+" rooms, category:  "+d.getRoomType()+".\n");
        }
        return out;
    }
    // Cancels a booking that has been made by a guest.
    @Override
    public String cancel(String type, int number, String name)throws java.rmi.RemoteException{
        String answer = null;
        boolean exist = false;
        int pos = 0;
        for(Customer d : custList){
            if(d.getName()!=null && d.getName().equalsIgnoreCase(name)){
                exist = true;
                pos = custList.indexOf(d);
            }
        }
        if(!exist){
            answer = "There is no Customer with that name.";
            return answer;
        }
        Customer MyCustomer = custList.get(pos);
        if(MyHotel.cancel(MyCustomer, type, number)){
            MyHotel.updateAvail(type, -number);
            MyCustomer.setRoomCount(MyCustomer.getRoomCount() - number);
            /*notifyHotelListeners(type);*/
        }
        // Prints the rooms that the guest has booked after the cancelation.
        answer = "Customers remaining rooms "+MyCustomer.getRoomCount()+".";
        return answer;
    }

}
