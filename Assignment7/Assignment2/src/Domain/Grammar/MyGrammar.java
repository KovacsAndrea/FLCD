package Domain.Grammar;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MyGrammar {
    private Set<String> N = new HashSet<>();
    private Set<String> E = new HashSet<>();
    private final HashMap<Set<String>, Set<List<String>>> P = new HashMap<>();
    private String S = "";

    public MyGrammar(String filename) {
        readFromFile(filename);
    }

    private void readFromFile(String filename) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String input = reader.readLine();
            String[] NlineSplit = input.split("=",input.indexOf("="));
            StringBuilder Nline = new StringBuilder();
            for(int i=1;i<NlineSplit.length;++i)
                Nline.append(NlineSplit[i]);
            StringBuilder builder = new StringBuilder(Nline.toString());
            builder.deleteCharAt(1).deleteCharAt(Nline.length()-2);
            Nline = new StringBuilder(builder.toString());
            this.N = new HashSet<>(Arrays.asList(Nline.toString().strip().split(" ")));

            input = reader.readLine();
            String[] resultArray = input.split("=",input.indexOf("="));
            StringBuilder result = new StringBuilder();
            for(int i=1;i<resultArray.length;++i)
                result.append(resultArray[i]);
            builder = new StringBuilder(result.toString());
            builder.deleteCharAt(1).deleteCharAt(result.length()-2);
            result = new StringBuilder(builder.toString());
            this.E = new HashSet<>(Arrays.asList(result.toString().strip().split(" ")));

            this.S = reader.readLine().split("=")[1].strip();

            reader.readLine();
            String line = reader.readLine();
            while(line != null){
                if(!line.equals("}")) {
                    String[] tokens = line.split("->");
                    String[] leftTokens = tokens[0].split(",");
                    String[] rightTokens = tokens[1].split("\\|");

                    Set<String> left = new HashSet<>();
                    for(String l : leftTokens)
                        left.add(l.strip());
                    if(!P.containsKey(left))
                        P.put(left,new HashSet<>());

                    for(String rightT : rightTokens) {
                        ArrayList<String> productionElements = new ArrayList<>();
                        String[] rightTokenElement = rightT.strip().split(" ");
                        for(String r : rightTokenElement)
                            productionElements.add(r.strip());
                        P.get(left).add(productionElements);
                    }
                }
                line = reader.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public Set<String> getN() {
        return N;
    }

    public Set<String> getE() {
        return E;
    }

    public HashMap<Set<String>, Set<List<String>>> getP() {
        return P;
    }


    public String getNonTerminals() {
        StringBuilder result = new StringBuilder("N = { ");
        for(String n : N)
            result.append(n).append(" ");
        result.append("}");
        return result.toString();
    }

    public String getTerminals() {
        StringBuilder result = new StringBuilder("E = { ");
        for(String e : E)
            result.append(e).append(" ");
        result.append("}");
        return result.toString();
    }

    public String getProductions() {
        StringBuilder result = new StringBuilder("P = { \n");
        P.forEach((left, right) -> {
            result.append("\t");
            int count = 0;
            for(String lh : left) {
                result.append(lh);
                count++;
                if(count<left.size())
                    result.append(", ");
            }
            result.append(" -> ");
            count = 0;
            for(List<String> rh : right){
                for(String r : rh) {
                    result.append(r).append(" ");
                }
                count++;
                if (count < right.size())
                    result.append("| ");

            }
            result.append("\n");
        });
        result.append("}");
        return result.toString();
    }

    public String getProductionsForNonTerminal(String nonTerminal){
        StringBuilder result = new StringBuilder();

        for(Set<String> left : P.keySet()) {
            if(left.contains(nonTerminal)) {
                result.append(nonTerminal).append(" -> ");
                Set<List<String>> right = P.get(left);
                int count = 0;
                for (List<String> rh : right) {
                    for(String r : rh) {
                        result.append(r).append(" ");
                    }
                    count++;
                    if (count < right.size())
                        result.append("| ");
                }
            }
        }

        return result.toString();
    }

    public boolean checkIfCFG(){
        boolean checkStartingSymbol = false;
        for(Set<String> left : P.keySet())
            if (left.contains(S)) {
                checkStartingSymbol = true;
                break;
            }
        if(!checkStartingSymbol)
            return false;

        for(Set<String> left : P.keySet()){
            if(left.size()>1)
                return false;
            else if(!N.contains(left.iterator().next()))
                return false;

            Set<List<String>> right = P.get(left);

            for(List<String> rh : right) {
                for (String r : rh) {
                    if(!(N.contains(r) || E.contains(r) || r.equals("EPSILON")))
                        return false;
                }
            }
        }
        return true;
    }

    public String getS() {
        return S;
    }

    public Set<List<String>> getProductionForNonterminal(String nonTerminal) {
        for (Set<String> left : P.keySet()) {
            if (left.contains(nonTerminal)) {
                return P.get(left);
            }
        }
        return new HashSet<>();
    }
}