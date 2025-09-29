package assignments.assignment2.interfaces;

public interface PriorityQueue<E> {
    int size();
    boolean isEmpty();
    void insert (double priority, E element);
    E removeMaxPriority();
}
