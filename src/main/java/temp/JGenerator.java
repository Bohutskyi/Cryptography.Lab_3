package temp;

import java.util.ArrayList;
import java.util.List;

public class JGenerator {
    private int N1 = 112;
    private int N2 = 95;
    private int C1 = 38;
    private int C2 = 33;
    private List<Integer> Z = new ArrayList<Integer> ();

    JGenerator(int N1, int N2, int C1, int C2, String Zstr) {
        for (int i = 0; i < Zstr.length (); i++) {
            Z.add (Zstr.charAt (i) - '0');
        }
        this.N1 = N1;
        this.N2 = N2;
        this.C1 = C1;
        this.C1 = C2;
    }

    public List<List<Integer>> computeKeys() {
        List<ArrayList<Integer>> l1Cands = getCandidatesForKeys (new L1 (), N1, C1);
        List<ArrayList<Integer>> l2Cands = getCandidatesForKeys (new L2 (), N2, C2);
        return getLastKey (l1Cands, l2Cands);
    }

    private List<Integer> getJiffySeq(LFRS l1, LFRS l2, LFRS l3, int N) {
        List<Integer> res = new ArrayList<Integer> ();
        for (int i = 0; i < N; i++) {
            res.add (getJiffyNext (l1, l2, l3));
        }
        return res;
    }

    private int getJiffyNext(LFRS l1, LFRS l2, LFRS l3) {
        int x = l1.getNext ();
        int y = l2.getNext ();
        int s = l3.getNext ();
        return s & x ^ (1 ^ s) & y;
    }

    private List<ArrayList<Integer>> getAllBinaryVectors(int len) {
        List<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>> ();
        for (int i = 0; i < (1 << len); i++) {
            ArrayList<Integer> currNumb = new ArrayList<Integer> ();
            for (int cI = 0; cI < len; cI++) {
                currNumb.add (0);
            }
            int number = i;
            for (int numbI = 0; number != 0; numbI++, number >>= 1) {
                currNumb.set (numbI, number & 1);
            }
            numbers.add (currNumb);
        }
        return numbers;
    }

    private List<List<Integer>> getLastKey(List<ArrayList<Integer>> l1cands, List<ArrayList<Integer>> l2cands) {
        LFRS l1 = new L1 ();
        LFRS l2 = new L2 ();
        LFRS l3 = new L3 ();
        List<ArrayList<Integer>> Xs = getAllBinaryVectors (l3.getInitSize ());
        List<List<Integer>> keys = new ArrayList<List<Integer>> ();
        List<Integer> currJiffySeq;
        for (ArrayList<Integer> l1cand : l1cands) {
            for (ArrayList<Integer> l2cand : l2cands) {
                for (int i = 0; i < (1 << l3.getInitSize ()); i++) {
                    l1.init ((ArrayList) l1cand.clone ());
                    l2.init ((ArrayList) l2cand.clone ());
                    l3.init ((ArrayList) Xs.get (i).clone ());
                    currJiffySeq = getJiffySeq (l1, l2, l3, Z.size ());
                    if (currJiffySeq.equals (Z)) {
                        keys.add (l1cand);
                        keys.add (l2cand);
                        keys.add (Xs.get (i));
                    }
                }
            }
        }
        return keys;
    }

    private int getR(List<Integer> x, List<Integer> z) {
        int r = 0;
        for (int i = 0; i < x.size (); i++) {

            r += x.get (i) ^ z.get (i);
        }
        return r;
    }

    private List<ArrayList<Integer>> getCandidatesForKeys(LFRS L, int N, int C) {
        List<ArrayList<Integer>> candidates = new ArrayList<ArrayList<Integer>> ();
        int Lsize = L.getInitSize ();
        List<ArrayList<Integer>> Xs = getAllBinaryVectors (Lsize);
        for (int i = 0; i < (1 << Lsize); i++) {
            L.init ((ArrayList) Xs.get (i).clone ());
            if (getR (L.getSeq (N), Z) < C) {
                candidates.add (Xs.get (i));
            }
        }
        return candidates;
    }
}
