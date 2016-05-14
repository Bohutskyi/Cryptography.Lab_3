import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CryptoAnalysis {

    private GeneratorDzhyffi generatorDzhyffi;
    private final static double ta = 2.326347874, tb1 = 6.009353488, tb2 = 6.120756286;

    public CryptoAnalysis(GeneratorDzhyffi generatorDzhyffi) {
        this.generatorDzhyffi = generatorDzhyffi;
    }

    public GeneratorDzhyffi getGeneratorDzhyffi() {
        return generatorDzhyffi;
    }

    public void doAnalysis(String s) {
        int[] temp = new int[s.length()];
        for (int i = 0, n = temp.length; i < n; i++) {
            temp[i] = Integer.parseInt(Character.toString(s.charAt(i)));
        }

        double NL1 = calculateN(tb1);
        double CL1 = calculateC(NL1);

        double NL2 = calculateN(tb2);
        double CL2 = calculateC(NL2);

        System.out.println(NL1);
        System.out.println(CL1);
        System.out.println(NL2);
        System.out.println(CL2);

//        ArrayList<int[]> results = new ArrayList<int[]>();
        ArrayList<int[]> results = analyzeRegister(10000000, 30, 0, NL1, CL1, temp);
//        ArrayList<int[]> results = analyzeRegister(10000000, 31, 1, NL2, CL2, temp);

//        analyzeRegister(1073741824, 30, 0, NL1, CL1, temp, results);

        /*Task.setValues(CL1, 30, NL1, temp);
        Thread thread1 = new Thread(new Task(new LinearFeedbackShiftRegister(generatorDzhyffi.get(0)), 3200000, 3300000));
        Thread thread3 = new Thread(new Task(new LinearFeedbackShiftRegister(generatorDzhyffi.get(0)), 3100000, 3200000));
        Thread thread2 = new Thread(new Task(new LinearFeedbackShiftRegister(generatorDzhyffi.get(0)), 3300000, 3400000));


        try {
            long time = - System.currentTimeMillis();
            thread1.start();
            thread2.start();
            thread3.start();
            thread1.join();
            thread2.join();
            thread3.join();
            time += System.currentTimeMillis();
            System.out.println("time = " + time);
//            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }*/

        System.out.println("****************************");
        for (int[] t1 : results) {
            for (int t2 : t1) {
                System.out.print(t2);
            }
            System.out.println();
        }


    }

    private ArrayList<int[]> analyzeRegister(int n, int size, int index, double NL, double CL, int[] temp) {
        Task.setValues(CL, size, NL, temp);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long time = - System.currentTimeMillis();
        for (int i = 0; i < n; i+= 100000) {
            executor.submit(new Task(new LinearFeedbackShiftRegister(generatorDzhyffi.get(index)), i, i + 100000));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        time += System.currentTimeMillis();
        System.out.println("time = " + time);
        return Task.getResults();
    }

    private void analyzeRegister(int n, int size, int index, double NL, double CL, int[] temp, ArrayList<int[]> results) {
        for (int i = 0; i < n; i++) {
            if (i % 100000 == 0) {
                System.out.println("i = " + i);
            }
            int current[] = getBinary(i, size);
            int R = 0;
            generatorDzhyffi.get(index).setVector(current);
            for (int j = 0; j < NL; j++) {
                R += temp[j] ^ generatorDzhyffi.get(index).getNext();
            }
            if (R < CL) {
                System.out.println("i = " + i);
                System.out.println("R = " + R + " , C = " + CL);
                results.add(current);
            }
        }
    }

    public static int[] getBinary(final int i, final int size) {
        int result[] = new int[size];
        String temp = Integer.toBinaryString(i);
        for (int j = size - 1, n = temp.length(); j >= size - n; j--) {
            result[j] = Character.digit((temp.charAt(-size + n + j)), 2);
        }
        return result;
    }

    private double calculateC(double N) {
        return (N / 4.) + (ta * Math.pow(3 * N, 0.5) / 4);
    }

    private double calculateN(double tb) {
        return 4. * tb * tb + 3. * ta * ta + 4. * ta * tb * Math.pow(3, 0.5);
    }


}
