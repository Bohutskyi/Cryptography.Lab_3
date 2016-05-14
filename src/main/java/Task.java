import java.util.ArrayList;

class Task implements Runnable {

    private static double CL, NL;
    private static int position;
    private static int[] temp;
    private static ArrayList<int[]> results = new ArrayList<int[]>();

    private final int startPoint, finishPoint;
    private LinearFeedbackShiftRegister register;

    public static void setValues(double CL, int position, double NL, final int[] temp) {
        Task.CL = CL;
        Task.position = position;
        Task.NL = NL;
        Task.temp = temp;
    }

    Task(LinearFeedbackShiftRegister register, int startPoint, int finishPoint) {
        this.register = register;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
    }

    public static ArrayList<int[]> getResults () {
        return results;
    }

    public void run() {
        for (int i = startPoint; i < finishPoint; i++) {
            if (i % 100000 == 0) {
                System.out.println(i);
            }
            int current[] = CryptoAnalysis.getBinary(i, position);
            int R = 0;
            register.setVector(current);
            for (int j = 0; j < NL; j++) {
                R += temp[j] ^ register.getNext();
            }
            if (R < CL) {
                results.add(current);
            }
        }
    }
}