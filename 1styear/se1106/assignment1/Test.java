public class Test {
    public static void main(String[] args) {
        Factor f1 = new Factor(3, 4);
        System.out.println(f1 + " (f1): " + f1.getValue());
        Factor f2 = new Factor(9, 2);
        System.out.println(f2 + " (f2): " + f2.getValue());

        System.out.println(f1.equals(f2) ? "f1 equals f2" : "f1 does not equal f2");
        System.out.println(f1.hasEqualValue(f2) ? "f1 has equal value with f2" : "f1 does not have equal value with f2");

        Factor f3 = f2.clone();
        System.out.println("f2 cloned (f3): " + f3);
        System.out.println("f3 base: " + f3.getBase() + ", f3 exponent: " + f3.getExponent());

        Factor f4 = new Factor(f3);
        System.out.println("f4 copy-constructed by f3: " + f4);

        f4.setBase(2);
        System.out.println("f4 after it's base is set to 2: " + f4);

        Number n1 = new Number(15);
        System.out.println("n1: " + n1 + ", toInteger: " + n1.toInteger());

        Number n2 = new Number(6);
        System.out.println("n1: " + n2 + ", toInteger: " + n2.toInteger());

        n1.multiplyBy(n2);
        System.out.println("after n1 multiplied by n2: " + n1 + ", toInteger: " + n1.toInteger());

        Number n3 = new Number(28);
        System.out.println("n1: " + n3 + ", toInteger: " + n3.toInteger());

        n1.add(n3);
        System.out.println("after n1 added with n3: " + n1 + ", toInteger: " + n1.toInteger());

        Number n4 = new Number(n3);
        System.out.println("n4 copy-constructed by n3: " + n4);
    }
}