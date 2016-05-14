package temp;

import java.util.ArrayList;
import java.util.List;

public class LFRS {
    protected List<Integer> L;
    protected int initSize;

    LFRS () {
    }

    public int getInitSize () {
        return initSize;
    }

    public void init (List<Integer> l) {
        L = l;
    }

    public List<Integer> getSeq (int N) {
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            arr.add(getNext());
        }
        return arr;
    }

    public int getNext () {
        return 0;
    }
}

class L1 extends LFRS {
    L1 () {
        initSize = 11;
    }

    public int getNext () {
        int Ln = L.get(2) ^ L.get(0);
        int L0 = L.get(0);
        for (int i = 0; i < L.size() - 1; i++) {
            L.set(i, L.get(i + 1));
        }
        L.set(L.size() - 1, Ln);
        return L0;
    }
}

class L2 extends LFRS {
    L2 () {
        initSize = 9;
    }

    public int getNext () {
        int Ln = L.get(4) ^ L.get(3) ^ L.get(1) ^ L.get(0);
        int L0 = L.get(0);
        for (int i = 0; i < L.size() - 1; i++) {
            L.set(i, L.get(i + 1));
        }
        L.set(L.size() - 1, Ln);
        return L0;
    }
}

class L3 extends LFRS {
    L3 () {
        initSize = 10;
    }

    public int getNext () {
        int Ln = L.get(3) ^ L.get(0);
        int L0 = L.get(0);
        for (int i = 0; i < L.size() - 1; i++) {
            L.set(i, L.get(i + 1));
        }
        L.set(L.size() - 1, Ln);
        return L0;
    }
}


