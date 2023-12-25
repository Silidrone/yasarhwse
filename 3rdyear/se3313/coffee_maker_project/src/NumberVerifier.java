public class NumberVerifier implements TextVerifier {
    protected int maxDigitCount;

    NumberVerifier(int maxDigitCount) {
        this.maxDigitCount = maxDigitCount;
    }

    @Override
    public boolean verify(String str) {
        return str.matches("[0-9]+") && str.length() <= maxDigitCount;
    }
}
