package Core;

public class Pair<K, V> {

    private final K key;
    private final V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof final Pair<?, ?> p)){
            System.out.println("\nCOAIE NU S\n");
            return false;
        }
        return this.key.equals(p.key) && p.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return key.hashCode()+value.hashCode();
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
