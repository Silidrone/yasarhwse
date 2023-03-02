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
interface BaseController {
    public boolean validate(ArrayList<String> input) throws Exception;
    public String create(ArrayList<String> input);
    public String showData();
}
