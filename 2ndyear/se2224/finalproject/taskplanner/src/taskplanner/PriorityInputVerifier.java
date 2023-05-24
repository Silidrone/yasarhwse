package taskplanner;

public class PriorityInputVerifier implements TextVerifier{
    @Override
    public boolean verify(String str) {
        return str.length() <= 3 && str.matches("[0-9]+");
    }
}
