/*
Tristan Shi
tms9908
September 19, 2025
CS-102 Data Structures and Algorithms
 */
package assignments.assignment1.CS102Assignment1_DynamicArrays_TristanShi;
public class DynamicList<E> extends ArrayList<E>{
    private int capacity;
    //default constructor creates an ArrayList of 1000 capacity
    public DynamicList() {
        //has to be in this order because super must be the first statement in a constructor of a child class
        //additionally I got a million compiler errors when i did it the other way around
        super (1000);
        capacity=1000;
    }
    //constructor that allows for specific size
    public DynamicList(int startSize){
        super(startSize);
        capacity=startSize;
    }
    //create a new Array of new_size, copy over data from previous array, set array to new array, and update capacity.
    //note: size stays the same because size only records how many positions in the array are non-null
    private void increaseArraySize(int new_size){
        @SuppressWarnings("unchecked")
        //only way to declare a generic datatype array and it's really dumb
        E[] newArray = (E[]) new Object[new_size];
        //copy the old array into this new higher-capacity array
        for (int i=0;i<array.length; i++){
            newArray[i]=array[i];
        }
        //make the array into the newArray
        array=newArray;
        //update capacity
        capacity = new_size;
    }
    @Override
    public void addFirst(E element) {
        //doubles size when full
        if (size==capacity){
            increaseArraySize(size*2);
        }
        //then super the method from the parent class ArrayList
        super.addFirst(element);
    }
    //test
    public static void main(String[] args){
        //bob
        DynamicList<Integer> bob = new DynamicList<>(2);
        for (int i = 1;i<=20;i++){
            bob.addFirst(i);
        }
        System.out.println(bob.capacity);
    }
}
