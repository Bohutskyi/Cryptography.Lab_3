package temp;

public class A {

    public static void f(int n) {
        assert (n > 0);
        int s = n * 2;
        System.out.println(s);
        System.out.println("Cool");
    }

    static int fact(int n) {
        assert (n >= 0);
        assert (n <= 10);
        if (n < 2) {
            return 1;
        }
        return fact(n - 1) * n;
    }

    public static void main (String[] args) {
        System.out.println(fact(5));
        System.out.println(fact(55));
        System.out.println(fact(-5));
        for (int i = -4; i < 40; i++) {
            System.out.println("i = " + i + ", " + fact(i));
        }
    }

}
