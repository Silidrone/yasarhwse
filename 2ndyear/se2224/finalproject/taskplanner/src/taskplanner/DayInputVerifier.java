package taskplanner;

public class DayInputVerifier implements TextVerifier{
    @Override
    public boolean verify(String str) {
        if(str.matches("[0-9]+")) {
            if(str.length() < 2) return true;
            else if(str.length() == 2) return Integer.parseInt(str) <= 31;
            else return false;
        }

        return false;
    }
}
