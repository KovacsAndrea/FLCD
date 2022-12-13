package Domain.FiniteAutomata.MyComponents;

import java.util.HashSet;
import java.util.Set;

public class Component<K>{
    protected Set<K> _components;

    public Component(){
        this._components = new HashSet<K>();
    }
    public Component(HashSet<K> _components){
        this._components = _components;
    }

    public boolean contains(K k){
        return _components.contains(k);
    }

    public void put(K k){
        _components.add(k);
    }

    public String toString(){
        {
            StringBuilder builder = new StringBuilder();
            for (K k : _components){
                builder.append(k).append(" ");
            }
            builder.append("\n");
            return builder.toString();
        }
    }
}
