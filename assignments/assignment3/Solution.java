package assignments.assignment3;
/*
Tristan Shi
10/08/25
CS102 - DSA
 */

public class Solution<E> {

    public static int numAli(String i, String j) {
        int a = i.length();
        int b = j.length();
        if (a == 0) {
            return 1;
        }
        if (b == 0) {
            return 0;
        }
        if (i.charAt(a - 1) == j.charAt(b - 1)) {
            return numAli(i.substring(0,a-1),j.substring(0,b-1))+numAli(i,j.substring(0,b-1));
        }
        else {
            return numAli(i, j.substring(0,b-1));
        }
    }
    //this function is inefficent because the calculations need to be repeated every single time ( so the complexity doubles with each run of this) and normally we would store the previous values in
    //arrays with dynamic programming or i guess maybe a hashmap? or a binary tree? idk anyways we need to store the previous values somehow instead of recalculating it every single recursion

    public static int numAliWithMaxGaps(String i, String j, int maxGaps, int gapsSoFar) {
        int a = i.length();
        int b = j.length();
        if (gapsSoFar > maxGaps) {
            return 0;
        }
        if (a == 0) {
            return 1;
        }
        if (b == 0) {
            return 0;
        }

        if (i.charAt(a - 1) == j.charAt(b - 1)) {
            return numAliWithMaxGaps(i.substring(0, a-1), j.substring(0, b-1), maxGaps, gapsSoFar)
                    + numAliWithMaxGaps(i, j.substring(0, b-1), maxGaps, gapsSoFar + 1); // Option 2: Gap
        } else {
            return numAliWithMaxGaps(i, j.substring(0, b-1), maxGaps, gapsSoFar + 1);
        }
    }
    //this numali is able to store max gaps by checking the amount of gaps each recursion and returning 0 if the gaps are greater than the
    //specified max gaps. everytime a character is skipped (i.e. they don't match) in a string, the gaps so far is increased by one, allowing us to count this.


    public static int totalNumGaps(String i, String j, int maxGaps, int gapsSoFar) {
        int a = i.length();
        int b = j.length();

        if (gapsSoFar > maxGaps) {
            return 0;
        }
        if (a == 0) {
            // All remaining chars in j are gaps
            if (gapsSoFar + b <= maxGaps) {
                return gapsSoFar + b;
            } else {
                return 0;
            }
        }
        if (b == 0) {

            return 0;
        }

        int total = 0;
        if (i.charAt(a - 1) == j.charAt(b - 1)) {
            total += totalNumGaps(i.substring(0, a - 1), j.substring(0, b - 1), maxGaps, gapsSoFar);
            total += totalNumGaps(i, j.substring(0, b - 1), maxGaps, gapsSoFar + 1);
        } else {
            total += totalNumGaps(i, j.substring(0, b - 1), maxGaps, gapsSoFar + 1);
        }
        return total;
    }
    //this function literaly just counts the amount of gapssofar in each permutation... not exactly rocket science


    static void main(String[] args){

        String a = "ABA";
        String b = "AABABA";
        System.out.println(numAli(a,b));



        System.out.println(numAliWithMaxGaps("ABBA", "AABAAABABBABA", 3, 0));
        System.out.println(totalNumGaps("ABA", "AABABA", 3, 0));

    }
}
