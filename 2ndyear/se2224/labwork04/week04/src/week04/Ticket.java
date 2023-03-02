/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week04;

import java.util.ArrayList;

/**
 *
 * @author jaglu
 */
public class Ticket {

    public int CAPACITY = 30;
    public int EXTRA = 20;
    public int ECONOMY = 500;
    public int BUSINESS = 1000;
    public int FIRST = 1500;
    private Flight flight;
    private int price;
    private int seat_number;
    private int luggage;

    Ticket(Flight flight, ArrayList<Integer> info) {
        this.flight = flight;
        seat_number = info.get(0);
        luggage = info.get(1);
        price = getCalculatedPrice();
    }

    private int getCalculatedPrice() {
        int total_price = 0;
        if (seat_number > 0) {
            if (seat_number <= 20) {
                total_price += FIRST;
            } else if (seat_number <= 70) {
                total_price += BUSINESS;
            } else if (seat_number <= 120) {
                total_price += ECONOMY;
            }
        }

        if (luggage > CAPACITY) {
            int extra_luggage = luggage - CAPACITY;
            total_price += Math.floor(extra_luggage / 3) * EXTRA;
        }
        return total_price;
    }

    public int getPrice() {
        return price;
    }

    public int getLuggage() {
        return luggage;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getSeatNumber() {
        return seat_number;
    }

    public String toString() {
        return flight.getFlightCode() + "<>" + Integer.toString(getPrice())
                + "<>" + Integer.toString(getLuggage());
    }
}
