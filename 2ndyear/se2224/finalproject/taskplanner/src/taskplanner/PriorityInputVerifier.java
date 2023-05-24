package taskplanner;

public class PriorityInputVerifier implements TextVerifier{
    @Override
    public boolean verify(String str) {
        return (new NumberVerifier(3)).verify(str);
    }
}
