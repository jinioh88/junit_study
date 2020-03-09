package ch2;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private Map<String, Answer> answers = new HashMap<>();
    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        score = 0;

        boolean kill = false;
        boolean anyMatches = false;
        for(Criterion criterion : criteria) {
            Answer answer = answerMatching(criterion);
            boolean match = criterion.matches(answer);

            if(!match && criterion.getWeight() == Weight.MustMatch) {
                kill = true;
            }

            if(match) {
                score += criterion.getWeight().getValue();
            }

            anyMatches |= match;
        }

        if(kill) {
            return false;
        }

        return anyMatches;
    }

    private Answer answerMatching(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public int score() {
        return score;
    }

}
