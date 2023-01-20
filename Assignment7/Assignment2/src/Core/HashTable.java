package Core;

import java.util.ArrayList;

public class HashTable {
    private final ArrayList<ArrayList<String>> _variablesAndConstants;
    private final int _size;

    public HashTable(int size){
        _size = size;
        _variablesAndConstants = new ArrayList<>();
        for(int i = 0; i<size; i++){
            _variablesAndConstants.add(new ArrayList<>());
        }
    }

    public int getSize(){
        return _size;
    }

    private int hash(String key){
        int sum = 0;
        for(int i = 0; i < key.length(); i++){
            sum += key.charAt(i);
        }
        return sum%_size;
    }

    public void add(String key){
        int hashValue = hash(key);

        if(!_variablesAndConstants.get(hashValue).contains(key)){
            _variablesAndConstants.get(hashValue).add(key);
        }
    }

    public boolean contains(String key){
        int hashValue = hash(key);

        return _variablesAndConstants.get(hashValue).contains(key);
    }

    public Pair<Integer, Integer> getPosition(String key){
        if (this.contains(key)){
            int listPosition = this.hash(key);
            int listIndex = 0;
            for(String el:this._variablesAndConstants.get(listPosition)) {
                if (!el.equals(key))
                    listIndex++;
                else
                    break;
            }
            return new Pair<>(listPosition, listIndex);
        }
        return new Pair<>(-1, -1);
    }

    public void remove(String key){
        int hashValue = hash(key);
        if(_variablesAndConstants.get(hashValue).contains(key)){
            _variablesAndConstants.get(hashValue).remove(key);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<_size; ++i) {
            result.append(i).append(": [");
            String separator = "";
            for(String item: _variablesAndConstants.get(i)){
                result.append(separator);
                separator = ", ";
                result.append(item);
            }
            result.append("]\n");
        }
        return result.toString();
    }
}
