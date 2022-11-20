package Domain.FiniteAutomata.MyComponents;

import java.util.HashSet;

public class State extends Component<String> {
    public State(){
        super();
    }
    public State(HashSet<String> _states){
        super(_states);
    }

    public String toString(){
        return "\tStates: " + super.toString();
    }
}
