package Core;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<Pair<Integer, Pair<Integer, Integer>>> pif = new ArrayList<>();

    public void add(Integer code, Pair<Integer, Integer> value) {
        Pair<Integer, Pair<Integer, Integer>> pair = new Pair<>(code, value);
        pif.add(pair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Pair<Integer, Pair<Integer, Integer>> pair : pif) {
            result.append(pair.getKey()).append(" -> (").append(pair.getValue().getKey()).append(", ").append(pair.getValue().getValue()).append(")\n");
        }
        return result.toString();
    }
}
