package assignments.assignment4.dependencies;

public class LinkedQueue<E> extends DoublyLinkedList<E> implements Queue<E>{
    public void enqueue(E element) {this.addFirst(element);}
    public E dequeue() {return this.removeLast();}
}