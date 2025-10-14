package assignments.assignment4.dependencies;

public interface Queue<E> {
    public abstract void enqueue(E element);
    public abstract E dequeue();
    public abstract int size();
}