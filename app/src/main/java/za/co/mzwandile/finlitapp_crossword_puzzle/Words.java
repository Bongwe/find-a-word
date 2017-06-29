package za.co.mzwandile.finlitapp_crossword_puzzle;

import java.util.LinkedList;
import java.util.List;

public class Words {
    List<String> answers;

    public Words() {
        this.answers = new LinkedList<>();
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswer(String answer) {
        this.answers.add(answer);
    }
}
