package Domain;

public record Pair<K, V>(K key, V value) {

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
            return false;
        }
        return this.key == p.key && p.value == this.value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
