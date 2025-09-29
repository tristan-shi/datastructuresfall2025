package assignments.assignment2;
import java.math.*;
import java.util.Collections;

public class RandomQueueHashMap<E> extends PriorityQueueHashMap<E>{
    public void insertAtRandom(E element){
        double position = Math.random()*Collections.max(data.keySet());
        this.insert(position,element);
    }
}
