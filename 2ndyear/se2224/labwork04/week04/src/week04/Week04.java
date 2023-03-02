/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package week04;

/**
 *
 * @author jaglu
 */
public class Week04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlightController fc = new FlightController();
        TicketController tc = new TicketController(fc);
        MainFrame mainFrame = new MainFrame(fc, tc);
        mainFrame.init();
    }
    
}
