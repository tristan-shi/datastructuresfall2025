package notes.recursion;

public class RecursionPlayground {
    //Recursive sum of digits
    public static int sumOfDigits(int n){
        if (n<10){return n;}
        return (n%10)+sumOfDigits(n/10);
    }
    //Recursive reverseString
    public static String reverseString(String s){
        if (s.length()==1){return s;}
        return s.charAt(s.length()-1)+reverseString(s.substring(0,s.length()-1));
    }
    //recursive char counter
    public static int countChar(String s, char c){
        if (s.isEmpty()){return 0;}
        for (int i =s.length()-1; i>0;i--){
            if (s.charAt(i)==c) {
                return 1 + countChar(s.substring(0, i - 1), c);
            }
        }
        return 0;
    }


    public static void main(String[] args){
       // System.out.println(sumOfDigits(1234));
        //System.out.println(reverseString("Java"));
        System.out.println(countChar("Banana",'a'));
    }
}
