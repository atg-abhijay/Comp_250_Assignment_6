import java.util.*;
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {23,1,5,102,20,56,7,15};
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

        
        int maxNumDigits = (int) Math.log10(max) + 1;           // cast + arith + arith             = 3
        LinkedList<LinkedList<Integer>> buckets = new LinkedList<LinkedList<Integer>>(); // assign  = 1
        for(int p = 0; p < 10; p++) {                           // 1 + 11 + 1                       = 13
            buckets.add(new LinkedList<Integer>());             // 10                               = 10
        }
        

        for(int j = 1; j <= maxNumDigits; j++) {                // 1 + (maxNumDigits+1) + maxNumDigits      = p 
            for(int k = 0; k < array.length; k++) {                 // 1 + (n+1) + n                        = p * (2n+2)
                int number = copied[k];                                             // index + assign       
                int digit = (int) ((number % Math.pow(10, j)) / Math.pow(10,j-1));  // cast + 5 * arith    
                buckets.get(digit).add(number);                                     // index + add          
            }                                                                       // lines 33-35:         = (pn) * 10

            int[] partiallySorted = new int[array.length];          // getlength +  assign                  = 2p
            int numAt = 0;                                          // assign                               = p

            search:
            for(int m = 0; m < array.length; m++) {                 // 1 + (n+1) + n                        = p * (2n+2)
                while(!buckets.get(m).isEmpty()) {                                  // index + method       = pn * 2
                    partiallySorted[numAt] = buckets.get(m).pollFirst();                // index + poll + index + assign   = pn * 2 * 4
                    numAt++;                                                            // arith                           = pn * 2
                }
                if(numAt == array.length) {                                         // getlength + cond + comp = pn * 3
                    break search;                                                       // 1                   = 1
                }
            }
            copied = partiallySorted;                              // assign                                = 1
        }

        return copied;                                             // return                                = 1
    }
}