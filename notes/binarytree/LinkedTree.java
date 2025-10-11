package notes.binarytree;
    import assignments.assignment2.DoublyLinkedList;
    import assignments.assignment2.PositionList;
    import assignments.assignment2.interfaces.Position;

public class LinkedTree<E> implements Tree<E> {
    Node<E> root;
    int size;
    private static class Node<E> implements Position<E>{
        private E element;
        private DoublyLinkedList<E> children;
        private Node<E> parent;
        public Node(E e, Node<E> p){
            element = e;
            parent = p;
            children = new DoublyLinkedList<>();

        }
        public E getElement(){
            return element;
        }
        public Node<E> getParent (){return parent;}
        public PositionList<E> getChildren(){return children;}
    } //end of node
    private Node<E> validatePosition(Position<E> p){
        if (p instanceof Node<E>){
            return (Node<E>)p;
        }
        else throw new IllegalArgumentException("This isn't a node");
    }
    public LinkedTree (E element){
        root = new Node<E>(element, null);
        size = 1;
    }
    public Position<E> root() {
        return root;
    }

    public Position<E> parent(Position<E> p) {
        return validatePosition(p).getParent();
    }
    public PositionList<Position<E>> children(Position<E> p) {
        return (PositionList<Position<E>>) validatePosition(p).getChildren();
    }

    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p)!=0;
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return !isInternal(p);
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return validatePosition(p)==root;
    }

    @Override
    public int numChildren(Position<E> p) {
        return validatePosition(p).getChildren().size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public PositionList<E> positions() {
        return null;
    }

    private static void main(String args[]){

    }
}
