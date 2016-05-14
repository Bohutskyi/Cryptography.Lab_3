public class GeneratorDzhyffi {

    private LinearFeedbackShiftRegister[] L;

    public GeneratorDzhyffi(LinearFeedbackShiftRegister[] l) {
        L = l;
    }

    public int getNext() {
        int temp = L[2].getNext();
        if (temp == 1) {
            L[1].getNext();
            return L[0].getNext();
        } else {
            L[0].getNext();
            return L[1].getNext();
        }
    }

    public LinearFeedbackShiftRegister get(int index) {
        if (index > L.length || index < 0) {
            return null;
        }
        return L[index];
    }

}
