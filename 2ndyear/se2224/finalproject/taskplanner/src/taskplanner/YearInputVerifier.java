package taskplanner;

public class YearInputVerifier implements TextVerifier {
    @Override
    public boolean verify(String str) {
        return str.matches("[0-9]+") && str.length() <= 4;
    }
}
