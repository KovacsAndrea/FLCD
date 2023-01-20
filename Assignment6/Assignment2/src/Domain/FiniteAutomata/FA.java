package Domain.FiniteAutomata;

import Domain.FiniteAutomata.MyComponents.Alphabet;
import Domain.FiniteAutomata.MyComponents.FinalState;
import Domain.FiniteAutomata.MyComponents.State;
import Domain.FiniteAutomata.MyComponents.Transition;
import Core.Pair;

import java.io.File;
import java.util.*;

public class FA{
    private State _states;
    private Alphabet _alphabet;
    private String _initialState;
    private FinalState _finalStates;
    private Transition _transitions;

    public FA(String filename) {
        this._states = new State();
        this._alphabet = new Alphabet();
        this._finalStates = new FinalState();
        this._transitions = new Transition();

        initFA(filename);
    }

    private void initFA(String filename){
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            String _statesLine = reader.nextLine();
            _states = new State(new HashSet<>(Arrays.asList(_statesLine.split(" "))));

            String _alphabetLine = reader.nextLine();
            _alphabet = new Alphabet(new HashSet<>(Arrays.asList(_alphabetLine.split(" "))));

            _initialState = reader.nextLine();

            String _finalStatesLine = reader.nextLine();
            _finalStates = new FinalState(new HashSet<>(Arrays.asList(_finalStatesLine.split(" "))));

            while(reader.hasNextLine()){
                String transitionLine = reader.nextLine();
                String[] transitionElements = transitionLine.split(" ");

                if(_states.contains(transitionElements[0]) && _states.contains(transitionElements[2]) && _alphabet.contains(transitionElements[1])) {

                    Pair<String, String> transitionStates = new Pair<>(transitionElements[0], transitionElements[1]);

                    if (!_transitions.containsKey(transitionStates)) {
                        Set<String> transitionStatesSet = new HashSet<>();
                        transitionStatesSet.add(transitionElements[2]);
                        _transitions.put(transitionStates, transitionStatesSet);
                    } else {
                        _transitions.get(transitionStates).add(transitionElements[2]);
                    }
                }
                else {
                    Arrays.stream(transitionElements).forEach(System.out::println);
                    System.out.println();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Transition getTransitions(){
        return _transitions;
    }

    public Alphabet getAlphabet(){
        return _alphabet;
    }

    public boolean isDFA(){
        System.out.println(this);
        return this._transitions.values().stream().noneMatch(list -> list.size() > 1);
    }

    public boolean accepts(String sequence){
        if(sequence.length() == 0)
            return _finalStates.contains(_initialState);

        String state = _initialState;
        for(int i=0;i<sequence.length();++i){
            Pair<String, String> key = new Pair<>(state, String.valueOf(sequence.charAt(i)));
            if(_transitions.containsKey(key)) {
                state = _transitions.get(key).iterator().next();
            }
            else
                return false;
        }
        return _finalStates.contains(state);
    }

    @Override
    public String toString() {
        return "Finite Automaton {\n" + _states +
                 _alphabet +
                "\tInitial State: " + _initialState + "\n" +
                _finalStates +
                _transitions.toString() +
                '}';
    }
}
