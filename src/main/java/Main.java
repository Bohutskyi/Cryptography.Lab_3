import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        LinearFeedbackShiftRegister registerL1 = new LinearFeedbackShiftRegister(30, 0, 2, 4, 6);
//        LinearFeedbackShiftRegister.printVector(registerL1.getCoefficients());

        LinearFeedbackShiftRegister registerL2 = new LinearFeedbackShiftRegister(31, 0, 3);
//        LinearFeedbackShiftRegister.printVector(registerL2.getCoefficients());

        LinearFeedbackShiftRegister registerL3 = new LinearFeedbackShiftRegister(32, 0, 1, 2, 3, 5, 7);
//        LinearFeedbackShiftRegister.printVector(registerL3.getCoefficients());

        GeneratorDzhyffi generator = new GeneratorDzhyffi(new LinearFeedbackShiftRegister[] {registerL1, registerL2, registerL3});

//        System.out.println(registerL1.getSize());
//        System.out.println(registerL2.getSize());
//        System.out.println(registerL3.getSize());

        CryptoAnalysis analysis = new CryptoAnalysis(generator);
        String s = "00100000111010111011101010010001000010110000010111111001000101101010101110110100100100100010001010011000101010111111100011111010000110110101000010101110011011000100101100000010011010010101110000000000010001010111111010010010011100110100100100011100000010011101010001111001101010110001101010001011000010001001001011101010011010101100110010000110011000110001010111100000010100000110111010101111010001100110101110010100001011111011010010110100111110101011111011001100011010110110010110010001010001001010100110111110100001001000011001100101100101101111001100011001010110000110111010011101110000110011010000111000101111111011011100001011011011101110001100010010011000001101111101000100101111110101011001101111000010101110000110011000111110101101100001101010101100010001011110100010001111110101011110101100101011000001100111001100101110011111110010110011100100111101010110111001001001001110001111001001011110110011111110000010010100111111010100011100001101000001011011111100011111111100111001100010110100001100110011001001110000100001110111000101010101011111101000101101110101100101100010001010111100100100001100111110101010111100000011100101111101110110010011101011001111101001001111100101100000100100010000011001110110111011011011101001100011100010000001001000000111011001110100001110001000001000100110001001100111100110110010011110101001110111110001010010101110110101111100010100010011111100010000010111001100111011101111010011011101000001110111111100010000000101001100011011100111001111011100011110001101110000100100010110111011010111110011101101100100001101101100100111100011100011100000100101000100101101010100111001110100101101111011010111101100001101000111011010111110001010000000101001010000001001100000000100100111001001011010001101100010010100010101100100110100111000010000000101000100000001111100000101010101111000111011001110110111001100110011110001101010100100011111100100101001001001001010111010010001101100011101111100010110011010001001010101001010000010011111010111010111100110111111001110110100100100000101111101111000001010111010110000";

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss:SS");
        Date date1 = new Date();
        System.out.println(dateFormat.format(date1));
        System.out.println("##################################");


        BigInteger i = new BigInteger("6678", 10);
        i = i.shiftRight(1);
        i = i.shiftLeft(1);
        System.out.println(i.toString(10));
//        analysis.doAnalysis(s);


        System.out.println("##################################");
        Date date2 = new Date();
        System.out.println(dateFormat.format(date2));
        System.out.println(dateFormat.format(date2.getTime() - date1.getTime()));


//        LinearFeedbackShiftRegister temp = new LinearFeedbackShiftRegister(4, 0, 1, 2, 3);
////        temp.setVector(3);
//        temp.setVector(0, 1, 2, 3);
//        System.out.println("*******************************");
//        LinearFeedbackShiftRegister.printVector(temp.getCoefficients());
//        LinearFeedbackShiftRegister.printVector(temp.getVector());
//        for (int i = 0; i < 7; i++) {
//            System.out.println("----------------------");
//            System.out.println(temp.getNext());
//            LinearFeedbackShiftRegister.printVector(temp.getVector());
//        }


    }
}
