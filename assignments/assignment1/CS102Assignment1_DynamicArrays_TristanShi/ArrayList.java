package assignments.assignment1.CS102Assignment1_DynamicArrays_TristanShi;

public class ArrayList<E> implements list<E> {
    E[] array;
    int size;
    //the squiggly lines were driving me crazy
    @SuppressWarnings("unchecked")
    //changed constructor for ArrayList to require an initialized size
    public ArrayList(int s){
        array = (E[ ]) new Object[s];
    }

    public int size() { return size; }
    public E getAtIndex(int index) {
        return array[index];
    }

    public void addLast(E element) {
        array[size] = element;
        size++;
    }

    public E removeLast() {
        E remove = array[size-1];
        array[size-1] = null;
        size--;
        return remove;
    }

    public E removeFirst() {
        E remove = array[0];
        for (int i=0; i<size; i++) {
            array[i] = array[i+1];
        }
        size--;
        return remove;
    }

    public E removeAtIndex(int i) {
        E remove = array[i];
        for (int j=i; j<size; j++) {
            array[j] = array[j+1];
        }
        size--;
        return remove;
    }

    public void addFirst(E element) {
        for (int i=size; i>0; i--) {
            array[i] = array[i-1];
        }
        array[0] = element;
        size++;
    }

    public boolean search(E element){
        for (int i=0; i< size; i++) {
            if (getAtIndex(i) == element) {
                return true;
            }
        }
        return false;
    }


    public static void main(String [] args) {

    }
}
