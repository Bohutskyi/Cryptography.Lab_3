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
        double NL1 = calculateN(tb1);
        double CL1 = calculateC(NL1);

        double NL2 = calculateN(tb2);
        double CL2 = calculateC(NL2);

        System.out.println(NL1);
        System.out.println(CL1);
        System.out.println(NL2);
        System.out.println(CL2);

        //For L1 and L2
////        long[] temp = new long[(int) NL1 + 1];
//        long[] temp = new long[(int) NL2 + 1];
//        for (int i = 0, n = temp.length; i < n; i++) {
//            temp[i] = Long.parseLong(Character.toString(s.charAt(i)), 2);
//        }
//
////        ArrayList<String> resultsL1 = analyzeRegister(1073741824, NL1, CL1, temp);
//
//        ArrayList<String> resultsL1 = analyzeRegister(2147483647, NL2, CL2, temp);
//
//        System.out.println("****************************");
//        System.out.println(resultsL1);
//
//        try (FileWriter writer = new FileWriter("Results/resultsL2.txt")) {
//            for (String buffer : resultsL1) {
//                writer.write(buffer + "\n");
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

        //For L3

    }

    private ArrayList<String> analyzeRegister(long n, double NL, double CL, long[] temp) {
        Task.setValues(CL, NL, temp);
        int step = 100000;
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
        long time = - System.currentTimeMillis();
        long i;
        for (i = 0; i < n; i+= step) {
//            executor.submit(new Task(new LinearFeedbackShiftRegister1(), i, i + step));
//            executor.submit(new Task(new LinearFeedbackShiftRegister2(), i, i + step));
        }
        if (i != n) {
//            executor.submit(new Task(new LinearFeedbackShiftRegister1(), i, i + (n % step)));
//            executor.submit(new Task(new LinearFeedbackShiftRegister2(), i, i + (n % step)));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        time += System.currentTimeMillis();
        System.out.println("time = " + time);
        return Task.getResults();
    }

//    private void analyzeRegister(int n, int size, int index, double NL, double CL, long[] temp, ArrayList<Long> results) {
//        for (long i = 230000000; i < n; i++) {
//            if (i % 100000 == 0) {
//                System.out.println("i = " + i);
//            }
////            int current[] = getBinary(i, size);
//            int R = 0;
//            generatorDzhyffi.get(index).setVector(i);
//            for (int j = 0; j < NL; j++) {
//                R += temp[j] ^ generatorDzhyffi.get(index).getCurrentBit();
//                generatorDzhyffi.get(index).getNext();
//            }
//            if (R < CL) {
//                System.out.println("i = " + i);
//                System.out.println("R = " + R + " , C = " + CL);
//                results.add(i);
//            }
//        }
//    }


    private double calculateC(double N) {
        return (N / 4.) + (ta * Math.pow(3 * N, 0.5) / 4);
    }

    private double calculateN(double tb) {
        return 4. * tb * tb + 3. * ta * ta + 4. * ta * tb * Math.pow(3, 0.5);
    }


}
