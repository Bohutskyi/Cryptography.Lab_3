public class GeneratorDzhyffi {

    private LinearRegister[] L;

    public GeneratorDzhyffi(LinearRegister[] l) {
        L = l;
    }

    public long getNext() {
        L[0].getNext();
        L[1].getNext();
        L[2].getNext();
        if (L[2].getCurrentBit() == 1) {
            return L[0].getCurrentBit();
        } else {
            return L[1].getCurrentBit();
        }
    }

}
