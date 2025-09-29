package assignments.assignment2;

import assignments.assignment2.interfaces.Queue;

import java.util.Collections;

public class PriorityQueueHashMapQueue<E> extends PriorityQueueHashMap<E> implements Queue <E> {
    //enqueue adds a new lowest priority element
    public void enqueue(E element) {
        double p = Collections.min(data.keySet());
        insert (p,element);
    }

    //removes highest priority
    public E dequeue() {
        return removeMaxPriority();
    }
}
