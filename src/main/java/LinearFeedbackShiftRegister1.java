public class LinearFeedbackShiftRegister1 implements LinearRegister {

    private long vector;
    private static final int n = 30;

    public void setVector(long vector) {
        this.vector = vector;
    }

    public long getVector() {
        return vector;
    }

    public long getCurrentBit() {
        return vector & 1;
    }

    public long getNext() {
        long temp = ((vector >> 6) & 1) ^ ((vector >> 4) & 1) ^ ((vector >> 1) & 1) ^ ((vector >> 0) & 1);
        temp = temp & 1;
        vector = vector >> 1;
        vector = (vector ^ (temp << (n - 1)));
        return vector;
    }

}
