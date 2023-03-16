/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

/**
 *
 * @author jaglu
 */
public class MDate {
    int month;
    int day;
    int year;
    
    MDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    public String toString() {
        String s_month = Integer.toString(month);
        if(month <= 9) {
            s_month = "0" + s_month;
        }
        String s_day = Integer.toString(day);
        if(day <= 9) {
            s_day = "0" + s_day;
        }
        
        return s_month + "/" + s_day + "/" + year;
    }
}
