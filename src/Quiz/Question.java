package Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {
    private final String text;
    private final List<String> options;
    private int correctIndex;

    public Question(String text, List<String> options, int correctIndex) {
        this.text = text;
        this.options = new ArrayList<>(options);
        this.correctIndex = correctIndex;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void shuffleOptions(Random rng) {
        String correct = options.get(correctIndex);
        Collections.shuffle(options, rng);
        this.correctIndex = options.indexOf(correct);
    }
}
