package ch2;

import java.util.Map;

public class MatchSet {
    private AnswerCollection answers;
    private Criteria criteria;

    public MatchSet(AnswerCollection answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
    }

    public int getScore() {
        int score = 0;
        for(Criterion criterion : criteria) {
            if(criterion.matches(answers.answerMatching(criterion))) {
                score += criterion.getWeight().getValue();
            }
        }

        return score;
    }

    public boolean matches() {
        if(doesNotMeetAnyMustMatchCriterion(criteria)) {
            return false;
        }

        return anyMatches(criteria);
    }

    private boolean doesNotMeetAnyMustMatchCriterion(Criteria criteria) {
        for(Criterion criterion : criteria) {
            boolean match = criterion.matches(answers.answerMatching(criterion));

            if(!match && criterion.getWeight() == Weight.MustMatch) {
                return true;
            }
        }
        return false;
    }

    private boolean anyMatches(Criteria criteria) {
        boolean anyMatches = false;
        for(Criterion criterion : criteria) {
            anyMatches |= criterion.matches(answers.answerMatching(criterion));
        }

        return anyMatches;
    }
}


