import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CryptoAnalysis {

    private GeneratorDzhyffi generatorDzhyffi;
    private final static double ta = 2.326347874, tb1 = 6.009353488, tb2 = 6.120756286;
    private final static int THREADS_COUNT = 15;

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

//        ArrayList<String> resultsL1 = analyzeRegister(1073741824, 30, 0, NL1, CL1, temp, THREADS_COUNT);
        ArrayList<String> resultsL1 = analyzeRegister(1000000, 30, 0, NL1, CL1, temp, THREADS_COUNT);
//        ArrayList<String> resultsL2 = analyzeRegister(2147483647, 31, 1, NL2, CL2, temp, THREADS_COUNT);
//        ArrayList<String> resultsL2 = analyzeRegister(1000000, 31, 1, NL2, CL2, temp, THREADS_COUNT);

//        analyzeRegister(1073741824, 30, 0, NL1, CL1, temp, resultsL1);
//        analyzeRegister(10000000, 310, 0, NL1, CL1, temp, resultsL1);

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

        try (FileWriter writer = new FileWriter("resultsL2.txt")) {
            for (String buffer : resultsL1) {
                writer.write(buffer + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

//    private ArrayList<int[]> analyzeRegister(int n, int size, int index, double NL, double CL, int[] temp) {
    private ArrayList<String> analyzeRegister(int n, int size, int index, double NL, double CL, int[] temp, final int threadCount) {
        Task.setValues(CL, size, NL, temp);
        int step = 100000;
//        ExecutorService executor = Executors.newFixedThreadPool(15);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        long time = - System.currentTimeMillis();
        int i;
        for (i = 0; i < n; i+= step) {
            executor.submit(new Task(new LinearFeedbackShiftRegister(generatorDzhyffi.get(index)), i, i + step));
        }
        if (i != n) {
            executor.submit(new Task(new LinearFeedbackShiftRegister(generatorDzhyffi.get(index)), i, i + (n % step)));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        time += System.currentTimeMillis();
        System.out.println("time = " + time);
        return Task.getResults();
    }

    private void analyzeRegister(int n, int size, int index, double NL, double CL, int[] temp, ArrayList<int[]> results) {
        long time = - System.currentTimeMillis();
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
        time += System.currentTimeMillis();
        System.out.println("time = " + time);
    }

    public static int[] getBinary(final int i, final int size) {
        int result[] = new int[size];
        String temp = Integer.toBinaryString(i);
        for (int j = size - 1, n = temp.length(); j >= size - n; j--) {
            result[j] = Character.digit((temp.charAt(- size + n + j)), 2);
        }
        return result;
    }

    public static String toBinary(int i, int size) {
        StringBuilder result = new StringBuilder(Integer.toBinaryString(i));
        while (result.length() < size) {
            result.insert(0, '0');
        }
        return result.toString();
    }

    private double calculateC(double N) {
        return (N / 4.) + (ta * Math.pow(3 * N, 0.5) / 4);
    }

    private double calculateN(double tb) {
        return 4. * tb * tb + 3. * ta * ta + 4. * ta * tb * Math.pow(3, 0.5);
    }


}
