/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jaglu
 */
public class FlightController implements BaseController {
    private ArrayList<Flight> all_flights;
    private ArrayList<String> departure_cities;
    private ArrayList<String> destination_cities;

    FlightController() {
        all_flights = new ArrayList<>();
        departure_cities = new ArrayList<>();
        destination_cities = new ArrayList<>();
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader("cities.txt"));
            String line = reader.readLine();
            boolean read_departure_cities = false;
            boolean read_destination_cities = false;
            while (line != null) {
                if (line.equals("Departure")) {
                    read_departure_cities = true;
                    read_destination_cities = false;
                } else if (line.equals("Destination")) {
                    read_destination_cities = true;
                    read_departure_cities = false;
                } else {
                    if (read_departure_cities) {
                        departure_cities.add(line);
                    } else if (read_destination_cities) {
                        destination_cities.add(line);
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validate(ArrayList<String> input) throws Exception {
        String flight_code = input.get(0);
        String departure = input.get(1);
        String destination = input.get(2);

        boolean flight_exists = false;
        for (Flight f : all_flights) {
            if (f.getFlightCode().equals(flight_code)) {
                flight_exists = true;
                break;
            }
        }

        boolean departure_city_exists = false;
        for (String city : departure_cities) {
            if (city.equals(departure)) {
                departure_city_exists = true;
                break;
            }
        }

        boolean destination_city_exists = false;
        for (String city : destination_cities) {
            if (city.equals(destination)) {
                destination_city_exists = true;
                break;
            }
        }

        if (flight_exists) {
            throw new Exception("Error : Flight code is exist");
        }
        if (!departure_city_exists) {
            throw new Exception("Error : " + departure + " is not a valid departure city");
        }
        if (!destination_city_exists) {
            throw new Exception("Error : " + destination + " is not a valid destination city");
        }

        return true;
    }

    public String create(ArrayList<String> input) {
        try {
            boolean valid = validate(input);
            if (valid) {
                all_flights.add(new Flight(input));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    public String showData() {
        String result = "";
        for(Flight flight : all_flights) {
            result += flight + "\n";
        }
        
        return result;
    }

    public Flight getFlightByNumber(String flight_number) {
        for (Flight flight : all_flights) {
            if (flight.getFlightCode().equals(flight_number)) {
                return flight;
            }
        }

        return null;
    }
}
