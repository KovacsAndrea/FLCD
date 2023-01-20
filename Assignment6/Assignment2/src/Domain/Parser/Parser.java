package Domain.Parser;

import Domain.Grammar.MyGrammar;

import java.util.*;

public class Parser {
    private final MyGrammar grammar;
    public HashMap<String, Set<String>> first;
    private HashMap<String, Set<String>> follow;

    public Parser(MyGrammar grammar) {
        this.grammar = grammar;
        this.first = new HashMap<>();
        this.follow = new HashMap<>();

        FIRST();
        FOLLOW();
    }

    public void FIRST() {
        //initialization
        for (String nonterminal : grammar.getN()) {
            first.put(nonterminal, new HashSet<>());
            Set<List<String>> productionForNonterminal = grammar.getProductionForNonterminal(nonterminal);
            for (List<String> production : productionForNonterminal) {
                if (grammar.getE().contains(production.get(0)) || production.get(0).equals("EPSILON"))
                    first.get(nonterminal).add(production.get(0));
            }
        }

        var isChanged = true;
        while (isChanged) {
            isChanged = false;
            HashMap<String, Set<String>> newColumn = new HashMap<>();

            for (String nonterminal : grammar.getN()) {
                Set<List<String>> productionForNonterminal = grammar.getProductionForNonterminal(nonterminal);
                Set<String> toAdd = new HashSet<>(first.get(nonterminal));
                for (List<String> production : productionForNonterminal) {
                    List<String> rhsNonTerminals = new ArrayList<>();
                    String rhsTerminal = null;
                    for (String symbol : production)
                        if (this.grammar.getN().contains(symbol))
                            rhsNonTerminals.add(symbol);
                        else {
                            rhsTerminal = symbol;
                            break;
                        }
                    toAdd.addAll(concatSizeOne(rhsNonTerminals, rhsTerminal));
                }
                if (!toAdd.equals(first.get(nonterminal))) {
                    isChanged = true;
                }
                newColumn.put(nonterminal, toAdd);
            }
            first = newColumn;
        }
    }

    private Set<String> concatSizeOne(List<String> nonTerminals, String terminal) {
        if (nonTerminals.size() == 0)
            return new HashSet<>();
        if (nonTerminals.size() == 1) {
            return first.get(nonTerminals.iterator().next());
        }

        Set<String> concatenation = new HashSet<>();
        var step = 0;
        var allEpsilon = true;

        for (String nonTerminal : nonTerminals) {
            if (!first.get(nonTerminal).contains("EPSILON")) {
                allEpsilon = false;
            }
        }
        if (allEpsilon) {
            concatenation.add(Objects.requireNonNullElse(terminal, "EPSILON"));
        }

        while (step < nonTerminals.size()) {
            boolean thereIsOneEpsilon = false;
            for (String s : first.get(nonTerminals.get(step)))
                if (s.equals("EPSILON"))
                    thereIsOneEpsilon = true;
                else
                    concatenation.add(s);

            if (thereIsOneEpsilon)
                step++;
            else
                break;
        }
        return concatenation;
    }

    public void FOLLOW() {
        for (String nonterminal : grammar.getN()) {
            follow.put(nonterminal, new HashSet<>());
        }
        follow.get(grammar.getS()).add("EPSILON");

        var isChanged = true;
        while (isChanged) {
            isChanged = false;
            HashMap<String, Set<String>> newColumn = new HashMap<>();

            for (String nonterminal : grammar.getN()) {
                newColumn.put(nonterminal, new HashSet<>());
                var productionsWithNonterminalInRhs = new HashMap<String, Set<List<String>>>();
                var allProductions = grammar.getP();
                allProductions.forEach((k, v) -> {
                    for (var eachProduction : v) {
                        if (eachProduction.contains(nonterminal)) {
                            var key = k.iterator().next();
                            if (!productionsWithNonterminalInRhs.containsKey(key))
                                productionsWithNonterminalInRhs.put(key, new HashSet<>());
                            productionsWithNonterminalInRhs.get(key).add(eachProduction);
                        }
                    }
                });
                var toAdd = new HashSet<>(follow.get(nonterminal));
                productionsWithNonterminalInRhs.forEach((k, v) -> {
                    for (var production : v) {
                        var productionList = (ArrayList<String>) production;
                        for (var indexOfNonterminal = 0; indexOfNonterminal < productionList.size(); ++indexOfNonterminal)
                            if (productionList.get(indexOfNonterminal).equals(nonterminal)) {
                                if (indexOfNonterminal + 1 == productionList.size()) {
                                    toAdd.addAll(follow.get(k));
                                } else {
                                    var followSymbol = productionList.get(indexOfNonterminal + 1);
                                    if (grammar.getE().contains(followSymbol))
                                        toAdd.add(followSymbol);
                                    else {
                                        for (var symbol : first.get(followSymbol)) {
                                            if (symbol.equals("EPSILON"))
                                                toAdd.addAll(follow.get(k));
                                            else
                                                toAdd.addAll(first.get(followSymbol));
                                        }
                                    }
                                }
                            }
                    }
                });
                if (!toAdd.equals(follow.get(nonterminal))) {
                    isChanged = true;
                }
                newColumn.put(nonterminal, toAdd);
            }
            follow = newColumn;
        }
    }

    private String toStringFIRST() {
        StringBuilder builder = new StringBuilder();
        first.forEach((k, v) -> {
            builder.append(k).append(": ").append(v).append("\n");
        });
        return builder.toString();
    }

    private String toStringFOLLOW() {
        StringBuilder builder = new StringBuilder();
        follow.forEach((k, v) -> {
            builder.append(k).append(": ").append(v).append("\n");
        });
        return builder.toString();
    }

    @Override
    public String toString(){
        return "FIRST\n" + toStringFIRST() + "FOLLOW\n" + toStringFOLLOW();
    }
}
