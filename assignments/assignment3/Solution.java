package assignments.assignment3;

public class Solution {
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
    public static void main(String[] args){
        String a = "ABA";
        String b = "AABABA";
        System.out.println(numAli(a,b));
    }
}
