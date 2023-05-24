package taskplanner;

public class YearInputVerifier implements TextVerifier {
    @Override
    public boolean verify(String str) {
        return (new NumberVerifier(4)).verify(str);
    }
}
