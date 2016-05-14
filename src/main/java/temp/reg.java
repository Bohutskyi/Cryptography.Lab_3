package temp;
import java.util.*;
import java.io.*;
import java.math.RoundingMode.*;
import java.math.BigDecimal;
import java.math.BigDecimal;

public class reg {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        String sequence = "01111010010110110011011111111101000100000110000010101111110011001010111111011010000010010100111111011000001101001111110100110101111011110101100110011111110100001110000100000110010110000101100111000011000100111011110011000000001000010010001101011001111001001011010011111011111100101110111101011011010000011110000110100101011101011110011101000101000001111001001010111000001110101011001010011111000001011110100111111000100000111001101010010111111110010101001000100011101011000110101111001111111010100000";

        int[] arrayZ = new int[sequence.length()];
        for (int i = 0; i < arrayZ.length; i++) {
            arrayZ[i] = sequence.charAt(i) - 48;
        }

        int[][] arrayL1 = generatorL1();    // Послідовність L1  +
        int[][] arrayL2 = generatorL2();    // Послідовність L2  +
        int[][] arrayL3 = generatorL3();    // Послідовність L3  +
        

        int N_1 = 113; //+
        int N_2 = 97;// +
        int N_3 = 104;
        int C_1 = 38; //+
        int C_2 = 34; //+
        int C_3 = 36;

        int[][] array2 = generate_sequences_2(arrayL2, N_2, 9);
        int[][] array1 = generate_sequences_1(arrayL1, N_1, 11);
        int[][] array3 = generate_sequences_3(arrayL3, N_3, 10);
                
        System.out.println(array1[0].length);
        System.out.println(array2[0].length);
        System.out.println(array3[0].length);

        

        int[][] ch_array1 = checked_sequences(array1, arrayZ, C_1, N_1);
        int[][] ch_array2 = checked_sequences(array2, arrayZ, C_2, N_2);

  int[] get_R_table = get_R_table(array2, arrayZ, C_2, N_2);
for(int i=0;i<get_R_table.length;i++){

    if(get_R_table[i]<34){
        System.out.println(i+":"+ get_R_table[i]+"-TRUE");
    }
    
    
}


        
        System.out.println("ch_array1:" + ch_array1.length);
        System.out.println("ch_array2:" + ch_array2.length);
        
        System.out.println("L1");
        
        for(int i=0;i<ch_array1.length;i++){
            for(int j=0;j<ch_array1[i].length;j++){
                System.out.print(ch_array1[i][j]);
            }
            System.out.println(" ");
        }
         System.out.println(" L2");
        
        for(int i=0;i<ch_array2.length;i++){
            for(int j=0;j<ch_array2[i].length;j++){
                System.out.print(ch_array2[i][j]);
            }
            System.out.println("");
        }
        

        int[] ArrayL1 = new int[N_1];  //01111000011

        for (int j = 0; j < 113; j++) {
            ArrayL1[j] = ch_array1[0][j];

        }

        int[] L1correct = {0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1};
        int[] L2correct = {0, 1, 1, 1, 1, 1, 1, 0, 0};

        int[] ArrayL2 = new int[N_2];  //011111100
        for (int i = 0; i < 97; i++) {
            ArrayL2[i] = ch_array2[1][i];

        }

    /*    int[][] proper = find_proper_L3(array3, ArrayL1, ArrayL2, N_2);
        for (int i = 0; i < proper.length; i++) {
            for (int j = 0; j < proper[i].length; j++) {
                System.out.print(proper[i][j]);
            }
            System.out.println("");
        }

        int index = 0;
        boolean[] array = filter_L3(proper, arrayZ, N_2);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == true) {
                index = i;
            }
        }

        System.out.println("index is:" + index); // оприділяє індекс порядку в таблиці
        int[] ArrayL3 = new int[N_3];
        for (int i = 0; i < N_3; i++) {
            ArrayL3[i] = array3[index][i];
            System.out.print(ArrayL3[i]);
        }

        int z = recognise_start_3(arrayL3, ArrayL3, 104, 10);
        System.out.println("WOOW Z:"+z);
      
       for(int i=0;i<10;i++){
           System.out.print(arrayL3[z-1][i]);
       }
       
        */
    }

    
    
       public static int[] get_R_table(int[][] array_seq, int[] array_z, int C, int N) {  // провірка вірних послідовностей за критерієм
        int[] R_saver = new int[array_seq.length]; // для кожної послідовності свої R

        int tmp = 0;
        int r = 0;

        for (int i = 0; i < array_seq.length; i++) {

            for (int j = 0; j < array_seq[i].length; j++) {
                r = (array_seq[i][j] ^ array_z[j]);
                tmp = r + tmp;
            }
            R_saver[i] = tmp;
            tmp = 0;
            r = 0;

        }
    return R_saver;}
    
    
    
    
        public static int recognise_start_3(int[][] input_L, int [] ArrayL3 ,int N, int pow ) {  // всі можливі послідовності
        int[][] exit_array = new int[(int) Math.pow(2, (double) pow) - 1][N];          // result array
        int counter = 0;
        int count=0;
        for (int i=0;i <exit_array.length;i++) {
            count++;
            // result array
            int[][] tmp_array = new int[N][pow];//N-number //n //n+1 - polynom step 
            System.arraycopy(input_L[counter], 0, tmp_array[0], 0, pow);
            counter++;
            for (int l = 1; l < tmp_array.length; l++) {
                for (int m = 0; m < tmp_array[l].length - 1; m++) {
                    tmp_array[l][m] = tmp_array[l - 1][m + 1];

                }

                tmp_array[l][9] = (tmp_array[l - 1][3] ^ tmp_array[l - 1][0]);
                

            }
            for (int j = 0; j < exit_array[i].length; j++) {
                exit_array[i][j] = tmp_array[j][0];
            }
            
            int cc=0;
            for(int z=0;z<ArrayL3.length;z++){
                if(ArrayL3[z]==exit_array[i][z]){
                    cc++;
                }
            }
            if(cc==ArrayL3.length){
            return count;}
           
             
          
            
            
            
        }

        return 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    public static int[] Giffe(int[] l1, int[] l2, int[] l3, int N) {

        int[] Giffe = new int[N];
        for (int i = 0; i < Giffe.length; i++) {
            Giffe[i] = (l3[i] * l1[i]) ^ ((1 ^ l3[i]) * l2[i]);
        }
        return Giffe;
    }

    public static boolean[] filter_L3(int[][] L3, int[] arrayz, int N_2) {

        boolean[] check = new boolean[L3.length];

        for (int i = 0; i < L3.length; i++) {
            int counter = 0;
            for (int j = 0; j < L3[i].length; j++) {

                if (L3[i][j] == arrayz[j]) {
                    counter++;
                }
            }
            if (counter == N_2) {
                check[i] = true;
            }

        }
        return check;
    }

    public static int[][] find_proper_L3(int[][] array_l3, int[] array_l1, int[] array_l2, int N) {
        int[][] res = new int[array_l3.length][N];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = (array_l3[i][j] * array_l1[j]) ^ (1 ^ array_l3[i][j]) * array_l2[j];
            }
        }

        return res;
    }

    public static int[][] checked_sequences(int[][] array_seq, int[] array_z, int C, int N) {  // провірка вірних послідовностей за критерієм
        int[] R_saver = new int[array_seq.length]; // для кожної послідовності свої R

        int tmp = 0;
        int r = 0;

        for (int i = 0; i < array_seq.length; i++) {

            for (int j = 0; j < array_seq[i].length; j++) {
                r = (array_seq[i][j] ^ array_z[j]);
                tmp = r + tmp;
            }
            R_saver[i] = tmp;
            tmp = 0;
            r = 0;

        }

        boolean[] checker = new boolean[R_saver.length];
        int count = 0;

        for (int i = 0; i < R_saver.length; i++) {
            if (R_saver[i] < C) {
                checker[i] = true;
                count++;
            } else {
                checker[i] = false;
            }

        }

        int[][] exit_array = new int[count][N];

        int cc = 0;
        for (int i = 0; i < R_saver.length; i++) {
            if (checker[i] == true) {
                for (int j = 0; j < 1; j++) {
                    for (int m = 0; m < array_seq[j].length; m++) {
                        exit_array[cc][m] = array_seq[i][m];
                    }
                    cc++;
                }

            }
        }

        return exit_array;
    }

    public static int[][] generate_sequences_3(int[][] input_L, int N, int pow) {  // всі можливі послідовності
        int[][] exit_array = new int[(int) Math.pow(2, (double) pow) - 1][N];          // result array
        int counter = 0;
        for (int[] exit_array1 : exit_array) {
            // result array
            int[][] tmp_array = new int[N][pow];//N-number //n //n+1 - polynom step 
            System.arraycopy(input_L[counter], 0, tmp_array[0], 0, pow);
            counter++;
            for (int l = 1; l < tmp_array.length; l++) {
                for (int m = 0; m < tmp_array[l].length - 1; m++) {
                    tmp_array[l][m] = tmp_array[l - 1][m + 1];

                }

                tmp_array[l][9] = (tmp_array[l - 1][3] ^ tmp_array[l - 1][0]);
            }
            for (int j = 0; j < exit_array1.length; j++) {
                exit_array1[j] = tmp_array[j][0];
            }
        }

        return exit_array;
    }

    public static int[][] generate_sequences_1(int[][] input_L, int N, int pow) {  // всі можливі послідовності

        int[][] exit_array = new int[(int) Math.pow(2, (double) pow) - 1][N];          // result array
        int counter = 0;
        for (int[] exit_array1 : exit_array) {
            // result array
            int[][] tmp_array = new int[N][pow];//N-number //n //n+1 - polynom step 
            System.arraycopy(input_L[counter], 0, tmp_array[0], 0, pow);
            counter++;
            for (int l = 1; l < tmp_array.length; l++) {
                for (int m = 0; m < tmp_array[l].length - 1; m++) {
                    tmp_array[l][m] = tmp_array[l - 1][m + 1];

                }

                tmp_array[l][10] = (tmp_array[l - 1][2] ^ tmp_array[l - 1][0]);
            }
            for (int j = 0; j < exit_array1.length; j++) {
                exit_array1[j] = tmp_array[j][0];
            }
        }

        return exit_array;
    }

    public static int[][] generate_sequences_2(int[][] input_L, int N, int pow) {  // всі можливі послідовності
        int[][] exit_array = new int[(int) Math.pow(2, (double) pow) - 1][N];          // result array
        int counter = 0;
        for (int[] exit_array1 : exit_array) {
            // result array
            int[][] tmp_array = new int[N][pow];//N-number //n //n+1 - polynom step 
            System.arraycopy(input_L[counter], 0, tmp_array[0], 0, pow);
            counter++;
            for (int l = 1; l < tmp_array.length; l++) {
                for (int m = 0; m < tmp_array[l].length - 1; m++) {
                    tmp_array[l][m] = tmp_array[l - 1][m + 1];

                }

                tmp_array[l][8] = (tmp_array[l - 1][4] ^ tmp_array[l - 1][3] ^ tmp_array[l - 1][1] ^ tmp_array[l - 1][0]);
            }
            for (int j = 0; j < exit_array1.length; j++) {
                exit_array1[j] = tmp_array[j][0];
            }
        }

        return exit_array;
    }

    public static int[][] generatorL1() throws InterruptedException {    //Checked   // регистр генерирующий последовательности      x^11+x^2+1
        int n = 11;
        double max_Period = Math.pow(2, n) - 1;
        int[][] sequence = new int[(int) max_Period][n];

        for (int i = 0; i < n; i++) {
            sequence[0][i] = 1;

        }

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 0; j < sequence[i].length - 1; j++) {
                sequence[i][j] = sequence[i - 1][j + 1];
            }
            sequence[i][10] = (sequence[i - 1][0] ^ sequence[i - 1][2]);
        }

        return sequence;
    }

    public static int[][] generatorL2() throws InterruptedException {                 //x^9+x^4+x^3+x+1 // chECKED
        int n = 9;
        double max_Period = Math.pow(2, n) - 1;
        int[][] sequence = new int[(int) max_Period][n];

        for (int i = 0; i < n; i++) {
            sequence[0][i] = 1;

        }

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 0; j < sequence[i].length - 1; j++) {
                sequence[i][j] = sequence[i - 1][j + 1];
            }
            sequence[i][8] = (sequence[i - 1][4] ^ sequence[i - 1][3] ^ sequence[i - 1][1] ^ sequence[i - 1][0]);
        }

        return sequence;
    }

    public static int[][] generatorL3() throws InterruptedException {          // x^10+x^3+1   ChECKED 
        int n = 10;
        double max_Period = Math.pow(2, n) - 1;
        int[][] sequence = new int[(int) max_Period][n];

        for (int i = 0; i < n; i++) {
            sequence[0][i] = 1;

        }

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 0; j < sequence[i].length - 1; j++) {
                sequence[i][j] = sequence[i - 1][j + 1];
            }
            sequence[i][9] = (sequence[i - 1][3] ^ sequence[i - 1][0]);
        }

        return sequence;
    }

}
