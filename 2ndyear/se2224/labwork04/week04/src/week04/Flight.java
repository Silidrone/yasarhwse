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
public class Flight {
    private static int SEAT_COUNT = 120;
    private String flight_code;
    private String departure;
    private String destination;
    private boolean cancelled;
    private int[] seats;
    
    Flight(ArrayList<String> info) {
        cancelled = false;
        seats = new int[SEAT_COUNT];
        flight_code = info.get(0);
        departure = info.get(1);
        destination = info.get(2);
    }
    
    public String getFlightCode() {
        return flight_code;
    }
    
    public String getDeparture() {
        return departure;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }
    
    public String toString() {
        return getFlightCode() + "<>" + getDeparture() + "<>" + getDestination();
    }
}
