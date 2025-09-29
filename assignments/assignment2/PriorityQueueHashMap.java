package assignments.assignment2;

import assignments.assignment2.interfaces.PriorityQueue;

import java.util.Collections;
import java.util.HashMap;
//i used hashmaps in python so i thought I'd use one here too

public class PriorityQueueHashMap<E> implements PriorityQueue<E> {
    //create an empty hashmap that stores double position and some element
    HashMap<Double, E > data = new HashMap<>();
    //empty constructor
    public PriorityQueueHashMap(){}
    //using inbuilt hashmap methods
    public int size() {
        return data.size();
    }

//hashmap methods (or is this a collection method? idk)
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void insert(double p, E e) {
        data.put(p, e);
    }
    //pretty self explanatory
    public E removeMaxPriority() {
        double maxPriority = Collections.max(data.keySet());
        return data.remove(maxPriority);
    }
    public static void main(String[] args){
        PriorityQueueHashMap<Integer> test = new PriorityQueueHashMap<Integer>();
        double[] random = {3,4,2,7,6,8,5,9,1,0};
        for(int i = 1;i<=10;i++){
            test.insert(random[i-1],i);
        }
        while (!test.isEmpty()){
            System.out.println(test.removeMaxPriority());
        }
    }
}
