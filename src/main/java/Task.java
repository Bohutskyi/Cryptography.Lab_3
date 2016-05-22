import java.util.ArrayList;

class Task implements Runnable {

    private static double CL, NL;
    private static long[] temp;
    private static ArrayList<String> results = new ArrayList<>();

    private final long startPoint, finishPoint;
//    private LinearFeedbackShiftRegister1 register;
    private LinearRegister register;

    public static void setValues(double CL, double NL, final long[] temp) {
        Task.CL = CL;
        Task.NL = NL;
        Task.temp = temp;
    }

    Task(LinearRegister register, long startPoint, long finishPoint) {
        this.register = register;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
    }

    public static ArrayList<String> getResults () {
        return results;
    }

    public void run() {
        for (long i = startPoint; i < finishPoint; i++) {
            if (i % 10000000 == 0) {
                System.out.println(i);
            }
            long R = 0;
            register.setVector(i);
            for (int j = 0; j < NL; j++) {
                R += temp[j] ^ register.getCurrentBit();
                register.getNext();
            }
            if (R < CL) {
                results.add(Long.toBinaryString(i) + " " + R);
            }
        }
    }

}