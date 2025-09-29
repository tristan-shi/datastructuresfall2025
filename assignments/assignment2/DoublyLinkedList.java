package assignments.assignment2;
import assignments.assignment2.interfaces.Position;

import java.util.Iterator;
//i just used the doublylinkedlist in the git and then edited it
public class DoublyLinkedList<E> extends PositionList<E> implements Iterable<E>{
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n) {
            element = e; prev = p; next = n;
        }
        public E getElement() { return element; }
        public void setElement(E element) { this.element = element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    @Override
    //same as arraylist
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }
//i wanted to try to use the positions we implemented earlier to make this work... this was a big headache
    private class DoublyLinkedListIterator implements Iterator<E>{
        //store the index as a position
        private Position<E> current;
        //set current to first on constructor
        public DoublyLinkedListIterator(){
            current = first();
        }
    //current actually is the next element. i used bad naming conventions, i know
        public boolean hasNext() {
            return current!=null;
        }
    //return the current element and iterate forward
        public E next() {
            E element = current.getElement();
            current = after(current);
            return element;
        }
    }
    public DoublyLinkedList( ) {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public Position<E> first(){
        if(size>0)
            return header.next;
        else
            return null;
    }
    public Position<E> last(){
        if(size>0)
            return trailer.prev;
        else
            return null;
    }
    private Node<E> validate_position(Position<E> p){
        if(p instanceof Node<E>)
            return (Node<E>)p;
        else
            throw new IllegalArgumentException();
    }
    public Position<E> after(Position<E> p){
        Node<E> node = validate_position(p).next;
        if(node != trailer)
            return node;
        else
            return null;
    };
    public Position<E> before(Position<E> p){
        Node<E> node = validate_position(p).prev;
        if(node != header)
            return node;
        else
            return null;
    };
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst( ) {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }
    public E removeLast( ) {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev( );
        Node<E> successor = node.getNext( );
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement( );
    }

//	public E removeAtIndex(int index) {
//		if (index >= size)
//			return null;
//		Node<E> current_node = header;
//		for (int i=0; i<index+1; i++) {
//			current_node = current_node.getNext();
//		}
//		return remove(current_node);
//	}

    public E getAtIndex(int index) {
        if (index >= size)
            return null;
        Node<E> current_node = header;
        for (int i=0; i<index+1; i++) {
            current_node = current_node.getNext();
        }
        return current_node.getElement();
    }

    public void addAfter(Position<E> p, E e) {
        Node<E> node = validate_position(p);
        addBetween(e, node, node.next);
    };
    public void addBefore(Position<E> p, E e) {
        Node<E> node = validate_position(p);
        addBetween(e, node.prev, node);
    };
    public void set(Position<E> p, E e) {
        Node<E> node = validate_position(p);
        node.setElement(e);
    };
    public E remove(Position<E> p){
        Node<E> node = validate_position(p);
        return remove(node);
    }

    public Position<E>[] swap(Position<E> p1, Position<E> p2) {
        E e1 = p1.getElement();
        E e2 = p2.getElement();
        addAfter(p2, e1);
        addAfter(p1, e2);
        Position<E> new_p1 = after(p2);
        Position<E> new_p2 = after(p1);
        remove(p1);
        remove(p2);
        Position<E>[] result = (Position<E>[]) new Position[]{new_p1, new_p2};
        return result;
    }

//	public void pprint() {
//		Node<E> current_node = header.next;
//		while(current_node.getElement() != null) {
//			System.out.println(current_node.getElement());
//			current_node = current_node.next;
//		}
//	}

    public static void main(String [] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i=1;i<= 10; i++){
            list.addLast(i);
        }
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()){
            int element = iter.next();
            System.out.println(element);

    }
}
    }

