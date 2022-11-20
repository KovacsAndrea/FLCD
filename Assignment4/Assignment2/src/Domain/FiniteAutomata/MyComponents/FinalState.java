package Domain.FiniteAutomata.MyComponents;

import java.util.HashSet;

public class FinalState extends Component<String> {

    public FinalState(){
        super();
    }
    public FinalState(HashSet<String> _finalstates){
        super(_finalstates);
    }

    @Override
    public String toString(){
        return "\tFinal States: " + super.toString();
    }
}
