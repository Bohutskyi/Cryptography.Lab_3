import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Вектори над полем F(2)
 * Нумерація - від 0 до (n - 1)
 *
*/

public class LinearFeedbackShiftRegister {

    private ArrayList<Integer> vector;
//    private BigInteger vector;
    private final ArrayList<Integer> coefficients;
    private int n;

    public LinearFeedbackShiftRegister(LinearFeedbackShiftRegister temp) {
        this(temp.n, temp.coefficients);
    }

    public LinearFeedbackShiftRegister(int n, ArrayList<Integer> arrayList) {
        this.n = n;
        coefficients = new ArrayList<>(arrayList);
    }

    public LinearFeedbackShiftRegister(int n, Integer ... array) {
        this.n = n;
        coefficients = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            coefficients.add(0);
        }
        for (int i : array) {
            coefficients.set(i, 1);
        }
    }

    public void setVector(ArrayList<Integer> vector) {
        this.vector = vector;
    }

    public void setVector(String s) {
        vector = new ArrayList<>();
        for (int i = 0, n = s.length(); i < n; i++) {
            vector.add(Character.getNumericValue(s.charAt(i)));
        }
    }

    public void setVector(int[] array) {
        vector = new ArrayList<>();
        for (int i = 0, n = array.length; i < n; i++) {
            vector.add(array[i]);
        }
    }

    public void setVector(Integer ... array) {
        this.vector = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vector.add(0);
        }
        for (int i : array) {
            vector.set(i, 1);
        }
    }

    public ArrayList<Integer> getVector() {
        return vector;
    }

    public static void printVector(ArrayList arrayList) {
        for (Object o : arrayList) {
            System.out.print(o);
        }
        System.out.println();
    }

    public ArrayList<Integer> getCoefficients() {
        return coefficients;
    }

    public int getNext() {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp ^= coefficients.get(i) * vector.get(i);
        }
        vector.add(temp);
        return vector.remove(0);
    }

    public int getNext(ArrayList<Integer> arrayList) {
        this.setVector(arrayList);
        return getNext();
    }

    public int getSize() {
        return this.n;
    }

//    public static void main (String[] args) {
//        LinearFeedbackShiftRegister l1 = new LinearFeedbackShiftRegister(7, 5, 2, 3);
//        LinearFeedbackShiftRegister l2 = new LinearFeedbackShiftRegister(l1);
//        printVector(l1.getCoefficients());
//        printVector(l2.getCoefficients());
//        l1.coefficients.set(0, 1);
//        printVector(l1.getCoefficients());
//        printVector(l2.getCoefficients());
//    }

}
