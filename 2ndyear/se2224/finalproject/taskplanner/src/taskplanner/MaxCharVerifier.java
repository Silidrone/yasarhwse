package taskplanner;

public class MaxCharVerifier implements TextVerifier {
    private int maxChar;
    MaxCharVerifier(int maxChar) {
        this.maxChar = maxChar;
    }
    @Override
    public boolean verify(String str) {
        return str.length() <= maxChar;
    }
}
