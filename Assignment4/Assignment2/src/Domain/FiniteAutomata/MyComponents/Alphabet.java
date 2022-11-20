package Domain.FiniteAutomata.MyComponents;

import java.util.HashSet;

public class Alphabet extends Component<String> {
    public Alphabet(){
        super();
    }
    public Alphabet(HashSet<String> _alphabet){
        super(_alphabet);
    }

    public String toString(){
        return "\tAlphabet: " + super.toString();
    }
}
