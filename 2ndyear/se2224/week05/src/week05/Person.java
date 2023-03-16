/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 *
 * @author jaglu
 */
public class Person {
    protected String name;
    protected String surname;
    protected int month;
    protected int day;
    protected int year;
    
    Person(String name, String surname, int month, int day, int year) {
        this.name = name;
        this.surname = surname;
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    String getName() {
        return name;
    }
    
    String getSurname() {
        return surname;
    }
    
    String getDateString() {
        String s_month = Integer.toString(month);
        if(month <= 9) {
            s_month = "0" + s_month;
        }
        String s_day = Integer.toString(day);
        if(day <= 9) {
            s_day = "0" + s_day;
        }
        
        return month + "/" + day + "/" + year;
    }
    
    int getAge() {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR) - year;
    }
}
