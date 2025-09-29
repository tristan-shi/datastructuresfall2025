package assignments.assignment1;

public interface list<E> {
    abstract int size();

    abstract void addFirst(E element);

    abstract void addLast(E element);

    abstract E removeFirst();

    abstract E removeLast();

    abstract E getAtIndex(int Index);
}
