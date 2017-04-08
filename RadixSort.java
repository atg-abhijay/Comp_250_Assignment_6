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
        int max = 0;
        int[] copied = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            if(max < array[i]) {
                max = array[i];
            }
            copied[i] = array[i];
        }

        
        int maxNumDigits = (int) Math.log10(max) + 1;
        LinkedList<LinkedList<Integer>> buckets = new LinkedList<LinkedList<Integer>>();
        for(int p = 0; p < 10; p++) {
            buckets.add(new LinkedList<Integer>());
        }
        

        for(int j = 1; j <= maxNumDigits; j++) {
            for(int k = 0; k < array.length; k++) {
                int number = copied[k];
                int digit = (int) ((number % Math.pow(10, j)) / Math.pow(10,j-1));
                buckets.get(digit).add(number);
            }
            
            int[] partiallySorted = new int[array.length];
            int numAt = 0;
            search:
            for(int m = 0; m < array.length; m++) {
                while(!buckets.get(m).isEmpty()) {
                    partiallySorted[numAt] = buckets.get(m).pollFirst();
                    numAt++;
                }
                if(numAt == array.length) {
                    break search;
                }
            }
            copied = partiallySorted;
        }

        return copied;
    }
}