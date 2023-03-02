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
public class TicketController implements BaseController {
    private FlightController f_c;
    private ArrayList<Ticket> all_tickets;
    
    TicketController(FlightController fc) {
        f_c = fc;
        all_tickets = new ArrayList<>();
    }

    public boolean validate(ArrayList<String> input) throws Exception {
        Flight flight = f_c.getFlightByNumber(input.get(0));
        if(flight == null) {
            throw new Exception("Error : Flight with that number does not exist");
        }
        int seat_number = Integer.parseInt(input.get(1));
        if(seat_number > 120 || seat_number < 0) {
            throw new Exception("Error : seat number cannot be > 120 or < 0");
        }
        int luggage = Integer.parseInt(input.get(2));
        if (luggage < 1) {
            throw new Exception("Error : Luggage cant be smaller than 1 kg");
        }
        if (flight.isCancelled()) {
            throw new Exception("Error : This flight is cancelled you can not buy ticket");
        }
        for (Ticket t : all_tickets) {
            if (t.getSeatNumber() == seat_number) { //seat number already bought
                throw new Exception("Error : This seat is taken please choose new seat");
            }
        }

        return true;
    }

    public String create(ArrayList<String> input) {
        try {
            boolean valid = validate(input);
            if (valid) {
                ArrayList<Integer> info = new ArrayList<>();
                Flight flight = f_c.getFlightByNumber(input.get(0));
                info.add(Integer.valueOf(input.get(1)));
                info.add(Integer.valueOf(input.get(2)));
                all_tickets.add(new Ticket(flight, info));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    public String showData() {
        String result = "";
        for(Ticket ticket : all_tickets) {
            result += ticket + "\n";
        }
        
        return result;
    }
}
