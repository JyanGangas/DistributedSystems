/****************************************************
 DESCRIPTION:	RMI Hotel
 AUTHOR:	John Gangas (AM: 19390038)
 CLASS:		ΧΠ
 DATE: 		
 ***************************************************/
package com.HotelBooking.HotelBooking.server;

public class Customer {
    private String name;
    private String roomType;
    private int roomCount;

    public Customer(String name, String roomType, int roomCount){
        this.name = name;
        this.roomCount = roomCount;
        this.roomType = roomType;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType(){
        return this.roomType;
    }

    public void setRoomType(String roomType){
        this.roomType = roomType;
    }

    public int getRoomCount(){
        return this.roomCount;
    }

    public void setRoomCount(int roomCount){
        this.roomCount = roomCount;
    }


}
