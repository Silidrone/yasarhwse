public class Factor {
    private int base;
    private int exponent;

    Factor(int base, int exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    Factor(Factor other) {
        base = other.base;
        exponent = other.exponent;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return exponent;
    }

    public int getValue() {
        int value = 1;
        for(int i = 1; i <= exponent; i++)
            value *= base;
        return value;
    }

    public Factor clone() {
        return new Factor(this);
    }

    public boolean equals(Factor other) {
        return base == other.base && exponent == other.exponent;
    }

    public boolean hasEqualValue(Factor other) {
        return getValue() == other.getValue();
    }

    public String toString() {
        return base + "^" + exponent;
    }
}