/*
Student Number: 21070006208
Name Surname: Muhamed Cicak
 */

public class GrayCode {
    public String[] grayCodeSequence(int bits) {
        if (bits < 1) {
            return null;
        }
        if(bits > 10) {
            System.out.println("Error, maximum bits: 10");
            return null;
        }
        if (bits == 1) {
            return new String[] {"0", "1"};
        }
        String[] _gcode = grayCodeSequence(bits-1);
        String[] gcode = new String[2*_gcode.length];
        for (int i = 0; i < _gcode.length; i++) {
            gcode[i] = "0" + _gcode[i];
            gcode[gcode.length-1-i] = "1" + _gcode[i];
        }
        return gcode;
    }
}
