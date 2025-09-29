package assignments.assignment1.CS102Assignment1_DynamicArrays_TristanShi;

public interface list<E> {
    abstract int size();

    abstract void addFirst(E element);

    abstract void addLast(E element);

    abstract E removeFirst();

    abstract E removeLast();

    abstract E getAtIndex(int Index);
}
