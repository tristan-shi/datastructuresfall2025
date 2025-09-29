package assignments.assignment2;


import assignments.assignment2.interfaces.Stack;

import java.util.Collections;

public class PriorityQueueHashMapStack<E> extends PriorityQueueHashMap<E> implements Stack<E> {
    //size already exists in the PQ hash implementation
    //pop removes the highest priority element
    public E pop() {
        return removeMaxPriority();
    }

    //push inserts a new highest priority element
    public void push(E element) {
        double p = isEmpty()? 0 : Collections.max(data.keySet())+0.1;
        insert(p,element);
    }
}
