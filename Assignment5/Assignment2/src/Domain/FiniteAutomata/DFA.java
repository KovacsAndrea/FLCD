package Domain.FiniteAutomata;

import Core.Pair;
import Domain.Settings.SETTINGS;

import java.util.HashSet;
import java.util.List;

public class DFA {
    public final FA _identifier = new FA(SETTINGS.DFA_FILES.IDENTIFIER_DFA.PATH);
    public final FA _string = new FA(SETTINGS.DFA_FILES.STRING_DFA.PATH);
    public final FA _char = new FA(SETTINGS.DFA_FILES.CHAR_DFA.PATH);
    public final FA _integer = new FA(SETTINGS.DFA_FILES.INTEGER_DFA.PATH);

    public DFA(){
        _char.getAlphabet().put(" ");
        _string.getAlphabet().put(" ");
        _char.getTransitions().put(new Pair<>("B", " "), new HashSet<>(List.of("C")));
        _string.getTransitions().put(new Pair<>("B", " "), new HashSet<>(List.of("B")));
    }
}
