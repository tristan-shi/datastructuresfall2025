package assignments.assignment4.dependencies;

public interface Stack<E> {
    public abstract E pop();
    public abstract void push(E element);
    public abstract int size();
}