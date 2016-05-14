package temp;

import java.util.List;

public class JGeneratorPresent {
    public static void main (String[] args) {
        int N1 = 112;
        int N2 = 95;
        int C1 = 38;
        int C2 = 33;
        String Zstr =
                "1011011001101100100011100111011110111000101101000111000010111011000010011011010100011100001011011011001000011011100100001001000100110001 01101110111100101001010100001000100011111100001010110100111000001100100101111101100010111110100010000010000111110000101001001000101101101 01001011011111110011011000101010010011001001011000101001101010101000100011111100110101110110001100110100101111100100010110110011001110000 101100100000101101101010111100001111010000101111101101100111001101011011110111011001111111";
        JGenerator jG = new JGenerator (N1, N2, C1, C2, Zstr);
        List<List<Integer>> result = jG.computeKeys ();
        System.out.println ("l1: " + result.get (0));
        System.out.println ("l2: " + result.get (1));
        System.out.println ("l3: " + result.get (2));
    }
}