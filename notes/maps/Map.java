package notes.maps;
import assignments.assignment3.PositionList;

public interface Map<K,V> {
    void put(K key, V value);
    V get (K key);
    V remove(K key);
    int size();
    boolean isEmpty();
    PositionList<K> keySet();
    PositionList<V> values();
    PositionList<Entry<K,V>>entrySet();

}
