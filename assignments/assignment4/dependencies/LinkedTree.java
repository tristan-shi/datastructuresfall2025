package assignments.assignment4.dependencies;


public class LinkedTree<E> implements Tree<E>{
    protected static class Node<E> implements Position<E>{
        private E element;
        private DoublyLinkedList<Position<E>> children;
        private Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            this.children = new DoublyLinkedList<Position<E>>();
        }

        public E getElement(){return element;}
        public Node<E> getParent() {return parent;}
        //added this setter
        public void setParent(Node<E> p){this.parent=p;}
        public PositionList<Position<E>> getChildren() {return children;}
    }

    protected Node<E> validate_position(Position<E> p){
        if(p instanceof Node<E>)
            return (Node<E>)p;
        else
            throw new IllegalArgumentException();
    }

    protected Node<E> root;
    protected int size;

    public LinkedTree(E root_element) {
        root = new Node<E>(root_element, null);
        size = 1;
    }

    public Position<E> root(){
        return root;
    }
    public Position<E> parent(Position<E> p){
        return validate_position(p).getParent();
    };
    public PositionList< Position<E> >  children(Position<E> p){
        return validate_position(p).getChildren();
    };
    public int numChildren(Position<E> p) {
        return validate_position(p).getChildren().size();
    }
    public boolean isInternal(Position<E> p) {
        return numChildren(p) != 0;
    }
    public boolean isExternal(Position<E> p) {
        return !isInternal(p);
    }
    public boolean isRoot(Position<E> p) {
        return p == root;
    }
    public int size() {return size;}
    public boolean isEmpty() { return size == 0;}

    public int depth(Position<E> p) {
        if (isRoot(p)) return 0;
        else return 1 + depth(parent(p));
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> child: children(p)) {
            h = Math.max(h, height(child));
        }
        return h+1;
    }

    public boolean depthFirstSearch(E element){
        Stack<Position<E>> stack = new LinkedStack<Position<E>>();
        stack.push(root);
        while (stack.size() > 0) {
            Position<E> next_pos = stack.pop();
            if (next_pos.getElement() == element) return true;
            for (Position<E> child: children(next_pos)) {
                stack.push(child);
            }
        }
        return false;
    }

    public boolean breadthFirstSearch(E element){
        Queue<Position<E>> queue = new LinkedQueue<Position<E>>();
        queue.enqueue(root);
        while (queue.size() > 0) {
            Position<E> next_pos = queue.dequeue();
            if (next_pos.getElement() == element) return true;
            for (Position<E> child: children(next_pos)) {
                queue.enqueue(child);
            }
        }
        return false;
    }

    //	Using recursion
    private PositionList<Position<E>> positionsRecursive(Position<E> p, PositionList<Position<E>> list){
        list.addLast(p);
        for (Position<E> child: children(p)) {
            positionsRecursive(child, list);
        }
        return list;
    }

    public PositionList<Position<E>> positionsRecursive(Position<E> p){
        PositionList<Position<E>> list = new DoublyLinkedList<Position<E>>();
        return positionsRecursive(p, list);
    }

    //	Using stacks
    private PositionList<Position<E>> positions(Position<E> p){
        PositionList<Position<E>> pos = new DoublyLinkedList<Position<E>>();
        Stack<Position<E>> stack = new LinkedStack<Position<E>>();
        stack.push(p);
        while (stack.size() > 0) {
            Position<E> next_pos = stack.pop();
            pos.addLast(next_pos);
            for (Position<E> child: children(next_pos)) {
                stack.push(child);
            }
        }
        return pos;
    }

    public PositionList<Position<E>> positions(){
        return positions(root);
    }
    //DFS
    public Position<E> findPosition(E element) {
        LinkedStack<Position<E>> stack = new LinkedStack<>();
        stack.push(root);
        while (stack.size() > 0) {
            Position<E> current = stack.pop();
            if (current.getElement().equals(element)) {
                return current;
            }
            for (Position<E> child : children(current)) {
                stack.push(child);
            }
        }
        return null; // Element not found
    }

}