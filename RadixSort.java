import java.util.*;
public class RadixSort {
    public static void main(String[] args) {
        int[] array = new int[8];
        //int[] array = {7,2,9,8};
        for(int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 50);
            System.out.print(array[i] + " ");
        }

        System.out.println("\n");
        int[] answer = radixSort(array);
        for(int k = 0; k < answer.length; k++) {
            System.out.print(answer[k] + " ");
        }
    }

    public static int[] radixSort(int[] array) {
        int max = 0;                                            // assign                   = 1 
        int[] copied = new int[array.length];                   // getlength + assign       = 2
        for(int i = 0; i < array.length; i++) {                 // 1 + (n+1) + n            = 2n + 2
            int num = array[i];                                     // index + assign
            if(max < num) {                                         // comp + cond
                max = num;                                          // assign
            }
            copied[i] = num;                                        // assign + index
        }                                                       // lines 16-20:             = 7n

        //System.out.println("Max: " + max);
        int maxNumDigits = (int) Math.log10(max) + 1;           // cast + arith + arith             = 3
        //System.out.println("Max number of digits: " + maxNumDigits);
        LinkedList<LinkedList<Integer>> buckets = new LinkedList<LinkedList<Integer>>(); // assign  = 1
        for(int p = 0; p < 10; p++) {                           // 1 + 11 + 1                       = 13
            buckets.add(new LinkedList<Integer>());             // 10                               = 10
        }
        

        for(int j = 1; j <= maxNumDigits; j++) {                // 1 + (maxNumDigits+1) + maxNumDigits      = p 
            for(int k = 0; k < array.length; k++) {                 // 1 + (n+1) + n                        = p * (2n+2)
                int number = copied[k];                                             // index + assign       
                //System.out.println("Number: " + number);
                int digit = (int) ((number % Math.pow(10, j)) / Math.pow(10,j-1));  // cast + 5 * arith    
                //System.out.println("Digit: " + digit);
                buckets.get(digit).add(number);                                     // index + add          
            }                                                                       // lines 33-35:         = (pn) * 10

            int[] partiallySorted = new int[array.length];          // getlength +  assign                  = 2p
            int numAt = 0;                                          // assign                               = p

            search:
            for(int m = 0; m < 10; m++) {                           // 1 + 11 + 10                          = p * (22)
                while(!buckets.get(m).isEmpty()) {                                  // index + method       = 10p * 2
                    partiallySorted[numAt] = buckets.get(m).pollFirst();                // index + poll + index + assign   = 10p * 2 * 4
                    //System.out.println("Added: " + partiallySorted[numAt]);
                    numAt++;                                                            // arith                           = 10p * 2
                }
                if(numAt == array.length) {                                         // getlength + cond + comp = 10p * 3
                    break search;                                                       // 1                   = 1
                }
            }
            copied = partiallySorted;                              // assign                                = 1
        }

        return copied;                                             // return                                = 1
    }
}