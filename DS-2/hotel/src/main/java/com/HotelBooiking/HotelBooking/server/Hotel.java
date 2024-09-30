/****************************************************
 DESCRIPTION:	RMI Hotel
 AUTHOR:	John Gangas (AM: 19390038)
 CLASS:		ΧΠ
 DATE: 		
 ***************************************************/
package com.HotelBooking.HotelBooking.server;

public class Hotel {

    int[] price = new int[5];
    int[] quantity = new int[5];

    protected Hotel(){
        // Price for each category A,B,C,D,E
        price[0] = 60;
        price[1] = 80;
        price[2] = 90;
        price[3] = 115;
        price[4] = 140;

        // Quantity for each category A,B,C,D,E
        // Single rooms
        quantity[0] = 25;
        // Double rooms
        quantity[1] = 40;
        // Twin rooms
        quantity[2] = 20;
        // Triple rooms
        quantity[3] = 15;
        // Quad rooms
        quantity[4] = 10;

    }

    public int getprice(String roomType){
        int pri = 0;
        switch (roomType) {
            case "A" -> pri = this.price[0];
            case "B" -> pri = this.price[1];
            case "C" -> pri = this.price[2];
            case "D" -> pri = this.price[3];
            case "E" -> pri = this.price[4];
            default -> {
            }
        }
        return pri;
    }

    public int getQuant(String roomType){
        int quant = 0;
        switch (roomType) {
            case "A" -> quant = this.quantity[0];
            case "B" -> quant = this.quantity[1];
            case "C" -> quant = this.quantity[2];
            case "D" -> quant = this.quantity[3];
            case "E" -> quant = this.quantity[4];
            default -> {
            }
        }
        return quant;
    }

    public void updateAvail(String roomType, int roomCount){
        switch (roomType) {
            case "A":
                this.quantity[0] = quantity[0] - roomCount;
                break;
            case "B":
                this.quantity[1] = quantity[1] - roomCount;
                break;
            case "C":
                this.quantity[2] = quantity[2] - roomCount;
                break;
            case "D":
                this.quantity[3] = quantity[3] - roomCount;
                break;
            case "E":
                this.quantity[4] = quantity[4] - roomCount;
                break;
            default:
                throw new IllegalStateException("UNEXPECTED VALUE: " + roomType);
        }
    }

    protected boolean book(String roomType, int roomCount){
        boolean answer = false;
        switch (roomType) {
            case "A" -> {
                if (this.quantity[0] >= roomCount) answer = true;
            }
            case "B" -> {
                if (this.quantity[1] >= roomCount) answer = true;
            }
            case "C" -> {
                if (this.quantity[2] >= roomCount) answer = true;
            }
            case "D" -> {
                if (this.quantity[3] >= roomCount) answer = true;
            }
            case "E" -> {
                if (this.quantity[4] >= roomCount) answer = true;
            }
            default -> answer = false;
        }
        return answer;
    }

    public boolean cancel(Customer customer,String roomType,int roomCount){
        // This class checks if the customers has booked the booking he is trying to cancel
        return customer.getRoomCount() >= roomCount && customer.getRoomType().equals(roomType);
    }

}