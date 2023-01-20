package Domain.FiniteAutomata.MyComponents;

import Core.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Transition {
    private final Map<Pair<String, String>, Set<String>> _transitions;
    public Transition(){
        _transitions = new HashMap<>();
    }
    public boolean containsKey(Pair<String, String> p1){
        return _transitions.containsKey(p1);
    }

    public void put(Pair<String, String> key, Set<String> values){
        _transitions.put(key,values);
    }

    public Set<String> get(Pair<String, String> key){
        return _transitions.get(key);
    }

    public Collection<Set<String>> values(){
        return _transitions.values();
    }
    public Collection<Pair<String, String>> keys(){
        return _transitions.keySet();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\tTransitions{\n");
        _transitions.forEach((K, V) -> {
            builder.append("\t\t(").append(K.getKey()).append(" ").append(K.getValue()).append(") -> ").append(V).append("\n");
        });
        builder.append("\t}\n");
        return builder.toString();
    }
}
