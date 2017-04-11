import java.util.*;
public class RadixSort {
    public static void main(String[] args) {
        /* int[] utilities = new int[8];
        int[] costs = new int[utilities.length];

        printArray(utilities, true);
        printArray(costs, true);

        int[] answer = radixSort(utilities);
        printArray(answer, false);

        System.out.println("\nGreedyAlgo");
        int[][] utilitiesAndCosts = sortForGreedyAlgo(utilities, costs);
        printArray(utilitiesAndCosts[0], false);
        printArray(utilitiesAndCosts[1], false); */

        int[] costs = {2,6,8,10};
        int[] util = {1,5,8,9};
        //Integer[] costsPerUtil = new Integer[costs.length];
        int n = Integer.parseInt(args[0]);
        int[] quantities = greedyChoice(costs, util, n);
        Integer[] q = new Integer[quantities.length];
        for(int i = 0; i < quantities.length; i++) {
            q[i] = Integer.valueOf(quantities[i]);
        }

        System.out.println();
        printArray(q, false);

        /* for(int i = 0; i < costsPerUtil.length; i++) {
            costsPerUtil[i] = (Integer) ((int)((costs[i] * 100.0)/util[i]));
        }
        //int[] q = greedyChoice(costs, util, n);
        //printArray(q, false);

        Integer[][] details = new Integer[4][4];
        for(int j = 0; j < details[0].length; j++) {
            for(int i = 0; i < details.length; i++) {
                if(j == 0) {
                    details[i][j] = objects[i];
                }
                if(j == 1) {
                    details[i][j] = costs[i];
                }
                if(j == 2) {
                    details[i][j] = util[i]; 
                }
                if(j == 3) {
                    details[i][j] = costsPerUtil[i];
                }
            }
        }


        for(int j = 0; j < details.length; j++) {
            printArray(details[j], false);
        }
        System.out.println();
        Integer[][] answer = sort(details);
        for(int j = 0; j < answer.length; j++) {
            printArray(answer[j], false);
        } */
        //sort2(details); 
        
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

    public static int[][] sortForGreedyAlgo(int[] utilities, int[] costs) {
        int max = 0;                         
        int[] copyForUtilities = new int[utilities.length];
        int[] copyForCosts = new int[costs.length];              
        for(int i = 0; i < copyForUtilities.length; i++) {                 
            int num = utilities[i];                                     
            if(max < num) {                                         
                max = num;                                          
            }
            copyForUtilities[i] = num;
            copyForCosts[i] = costs[i];                                      
        }                                                       

        int maxNumDigits = (int) Math.log10(max) + 1;           
        LinkedList<LinkedList<Integer>> bucketsForUtilities = new LinkedList<LinkedList<Integer>>();
        LinkedList<LinkedList<Integer>> bucketsForCosts = new LinkedList<LinkedList<Integer>>(); 
        for(int p = 0; p < 10; p++) {                           
            bucketsForUtilities.add(new LinkedList<Integer>());
            bucketsForCosts.add(new LinkedList<Integer>());
        }
        

        for(int j = 1; j <= maxNumDigits; j++) {                
            for(int k = 0; k < utilities.length; k++) {                 
                int number = copyForUtilities[k];                                                  
                int digit = (int) ((number % Math.pow(10, j)) / Math.pow(10,j-1));
                bucketsForUtilities.get(digit).add(number);
                bucketsForCosts.get(digit).add(copyForCosts[k]);                                         
            }                                                                      

            int[] partiallySortedU = new int[copyForUtilities.length];
            int[] partiallySortedC = new int[copyForCosts.length];
            int numAt = 0;                                          

            search:
            for(int m = 0; m < 10; m++) {                           
                while(!bucketsForUtilities.get(m).isEmpty()) {                                  
                    partiallySortedU[numAt] = bucketsForUtilities.get(m).pollFirst();   
                    partiallySortedC[numAt] = bucketsForCosts.get(m).pollFirst();
                    numAt++;                                                            
                }
                if(numAt == copyForUtilities.length) {                                         
                    break search;                                                       
                }
            }
            copyForUtilities = partiallySortedU;
            copyForCosts = partiallySortedC;
        }

        int[][] utilitiesAndCosts = new int[2][];
        utilitiesAndCosts[0] = copyForUtilities;
        utilitiesAndCosts[1] = copyForCosts;

        return utilitiesAndCosts;
    }

    public static int[] greedyChoice(int[] c, int[] u, int n) {
        Integer[] costs = new Integer[c.length];
        Integer[] util = new Integer[u.length];
        int[] q = new int[c.length];
        Integer[][] details = new Integer[c.length][4];
        Integer[] costsPerUtil = new Integer[c.length];
        for(int i = 0; i < costsPerUtil.length; i++) {
            costs[i] = Integer.valueOf(c[i]);
            util[i] = Integer.valueOf(u[i]);
            costsPerUtil[i] = (Integer) ((int)((costs[i] * 100.0)/util[i]));
        }

        for(int j = 0; j < details[0].length; j++) {
            for(int i = 0; i < details.length; i++) {
                if(j == 0) {
                    details[i][j] = i;
                }
                if(j == 1) {
                    details[i][j] = costs[i];
                }
                if(j == 2) {
                    details[i][j] = util[i]; 
                }
                if(j == 3) {
                    details[i][j] = costsPerUtil[i];
                }
            }
        }

        for(int j = 0; j < details.length; j++) {
            printArray(details[j], false);
        }

        System.out.println();
        Integer[][] answer = sort(details);

        for(int j = 0; j < answer.length; j++) {
            printArray(answer[j], false);
        }

        /* the method 'sort' sorts the 2D array 'details' based on
            its last column which is costPerUtil. all the other
            columns also get sorted in such a way so that they
            match the corresponding entries of the last column */

        for(int j = 0; j < q.length; j++) {
            int quantity = 0;
            int cost = answer[j][1];
            while(cost * quantity <= n) {
                quantity++;
            }

            if (quantity != 0) {
                quantity -= 1;
            }

            n = n - (int) (cost * quantity);
            int index = answer[j][0];
            q[index] = quantity;
        }
        return q;

    }

    public static void printArray(Integer[] array, boolean assign) {
        for(int i = 0; i < array.length; i++) {
            if(assign) {
                array[i] = (Integer) ((int) (Math.random()*20));
                //array[i] = (int) (Math.random()*20);
            }
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static Integer[][] sort(Integer[][] details) {
        int max = 0;
        Integer[] costPerUtil = new Integer[details.length];
        Integer[][] arrays = new Integer[details.length][details[0].length];          
        for(int i = 0; i < costPerUtil.length; i++) {                 
            int num = details[i][details[0].length - 1];                                     
            if(max < num) {                                         
                max = num;                                   
            }
            costPerUtil[i] = num;                     
        }

        for(int j = 0; j < details.length; j++) {
            arrays[j] = details[j];
        }                                        

        int maxNumDigits = (int) Math.log10(max) + 1;        
        LinkedList<LinkedList<Integer>> bucketsForCPerU = new LinkedList<LinkedList<Integer>>();
        LinkedList<LinkedList<Integer[]>> bucketsForArray = new LinkedList<LinkedList<Integer[]>>();
        for(int p = 0; p < 10; p++) {                           
            bucketsForCPerU.add(new LinkedList<Integer>());
            bucketsForArray.add(new LinkedList<Integer[]>());
        }
        

        for(int j = 1; j <= maxNumDigits; j++) {        
            for(int k = 0; k < costPerUtil.length; k++) {                 
                int number = costPerUtil[k];                                                  
                int digit = (int) ((number % Math.pow(10, j)) / Math.pow(10,j-1));
                bucketsForCPerU.get(digit).add(number);
                bucketsForArray.get(digit).add(arrays[k]);                                        
            }                                                                    

            Integer[] partiallySorted = new Integer[costPerUtil.length];
            Integer[][] parSorted = new Integer[details.length][details[0].length];
            int numAt = 0;                                          

            search:
            for(int m = 0; m < 10; m++) {                           
                while(!bucketsForCPerU.get(m).isEmpty()) {                                  
                    partiallySorted[numAt] = bucketsForCPerU.get(m).pollFirst();   
                    parSorted[numAt] = bucketsForArray.get(m).pollFirst();
                    numAt++;                                                            
                }
                if(numAt == costPerUtil.length) {                                         
                    break search;                                                       
                }
            }
            costPerUtil = partiallySorted;
            arrays = parSorted;
        }

        Integer[][] answer = arrays;

        return answer;
    }

    public static void sort2(Integer[][] theArray) {   
    //Integer[][] theArray = {{0,10},{1,9},{2,9},{3,9},{4,15},{5,10},{6,4},{7,8},{8,11},{9,12}};

    dump(theArray);
    Arrays.sort(theArray, new Comparator<Integer[]>()
    {
        @Override
        public int compare(Integer[] int1, Integer[] int2) {
            Integer numOfKeys1 = int1[int1.length];
            Integer numOfKeys2 = int2[int2.length];
            return numOfKeys1.compareTo(numOfKeys2);
        }
    });

        System.out.println("====");
        dump(theArray);     
    }

    public static void dump(Integer[][] array) {
        for(int p = 0; p < array.length; p++) {
            for(int q = 0; q < array[0].length; q++) {
                System.out.println(array[p][q] + " ");
            }
            System.out.println();
        }
    }

}