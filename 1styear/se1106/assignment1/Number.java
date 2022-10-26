import java.util.ArrayList;

public class Number {
    ArrayList<Factor> primeFactors = new ArrayList<>();

    Number(int n) {
        initFactors(n);
    }

    Number(Number other) {
        for (int i = 0; i < other.primeFactors.size(); i++) {
            primeFactors.add(other.primeFactors.get(i));
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < primeFactors.size(); i++) {
            result += primeFactors.get(i).toString();
            if(i < primeFactors.size() - 1)
                result += ".";
        }

        return result;
    }

    public int toInteger() {
        int value = 1;
        for (int i = 0; i < primeFactors.size(); i++)
            value *= primeFactors.get(i).getValue();

        return value;
    }

    public void multiplyBy(Number other) {
        for (int i = 0; i < other.primeFactors.size(); i++) {
            addFactor(other.primeFactors.get(i));
        }
    }

    public void add(Number other) {
        int newValue = toInteger() + other.toInteger();
        primeFactors.clear();
        initFactors(newValue);
    }

    protected void initFactors(int n) {
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                addFactor(new Factor(i, 1));
                n /= i;
            }
        }
    }

    protected void addFactor(Factor f) {
        Factor existentFactor = getSameBaseFactor(f);
        if (existentFactor != null) {
            existentFactor.setExponent(existentFactor.getExponent() + f.getExponent());
        } else {
            primeFactors.add(f);
        }
    }

    protected Factor getSameBaseFactor(Factor f) {
        for (int i = 0; i < primeFactors.size(); i++) {
            if (primeFactors.get(i).getBase() == f.getBase()) {
                return primeFactors.get(i);
            }
        }
        return null;
    }
}