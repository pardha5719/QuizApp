package Quiz;

import java.util.*;

public class Quiz {
    private final List<Question> questions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Random rng = new Random();
    private int score = 0;

    static class AnswerRecord {
        final Question q;
        final int chosenIndex;
        AnswerRecord(Question q, int chosenIndex) {
            this.q = q;
            this.chosenIndex = chosenIndex;
        }
    }

    private final List<AnswerRecord> history = new ArrayList<>();

    public void loadQuestions() {
        questions.add(new Question(
            "What are Java loops?",
            Arrays.asList(
                "Constructs to repeatedly execute code while a condition holds",
                "Keywords used only for exception handling",
                "Methods that return multiple values",
                "Annotations for compile-time checks"
            ),
            0
        ));

        questions.add(new Question(
            "What is the enhanced for-loop primarily used for?",
            Arrays.asList(
                "Iterating over arrays and Iterable collections",
                "Declaring generic types",
                "Handling checked exceptions",
                "Starting new threads"
            ),
            0
        ));

        questions.add(new Question(
            "Which is a good way to handle multiple user inputs from console?",
            Arrays.asList(
                "Use Scanner with validation loops",
                "Use System.gc() repeatedly",
                "Modify the JVM arguments",
                "Convert all inputs to enums without checks"
            ),
            0
        ));

        questions.add(new Question(
            "switch-case differs from if-else mainly in that switch-case…",
            Arrays.asList(
                "Selects a branch based on discrete values",
                "Can evaluate complex boolean expressions",
                "Always runs faster than if-else",
                "Is used only for strings"
            ),
            0
        ));

        questions.add(new Question(
            "What are Collections in Java?",
            Arrays.asList(
                "Framework of interfaces and classes for storing/manipulating groups of objects",
                "Only arrays in the java.lang package",
                "Bytecode instructions for the JVM",
                "A tool for network programming"
            ),
            0
        ));

        questions.add(new Question(
            "What is an ArrayList?",
            Arrays.asList(
                "A resizable array implementation of the List interface",
                "A synchronized map implementation",
                "A fixed-size primitive array",
                "A file handling utility"
            ),
            0
        ));

        questions.add(new Question(
            "How do you iterate using an Iterator?",
            Arrays.asList(
                "Obtain iterator() and loop while hasNext(), using next() inside",
                "Call iterate() directly on List",
                "Use for(int i : list) always",
                "Override equals() in the collection"
            ),
            0
        ));

        questions.add(new Question(
            "What is a Map?",
            Arrays.asList(
                "Key–value data structure that doesn’t allow duplicate keys",
                "An ordered list of values with duplicates",
                "A thread execution model",
                "A classloader configuration"
            ),
            0
        ));

        questions.add(new Question(
            "How to sort a List<String> ascending?",
            Arrays.asList(
                "Collections.sort(list)",
                "list.shuffle()",
                "Arrays.fill(list)",
                "System.sort(list)"
            ),
            0
        ));

        questions.add(new Question(
            "How to shuffle elements of a List?",
            Arrays.asList(
                "Collections.shuffle(list)",
                "List.rotate(list)",
                "Collections.sort(list)",
                "Map.randomize(list)"
            ),
            0
        ));
    }

    public void run() {
        Collections.shuffle(questions, rng);
        for (Question q : questions) q.shuffleOptions(rng);

        printHeader();
        int qNum = 1;

        for (Question q : questions) {
            System.out.println();
            System.out.println("Q" + qNum + ". " + q.getText());

            List<String> opts = q.getOptions();
            char label = 'A';
            for (String opt : opts) {
                System.out.println("  " + label + ") " + opt);
                label++;
            }

            int choiceIndex = readChoiceIndex(opts.size());
            history.add(new AnswerRecord(q, choiceIndex));

            if (choiceIndex == q.getCorrectIndex()) score++;
            qNum++;
        }

        printResult();
        reviewAnswers();
        promptRetake();
    }

    private void printHeader() {
        System.out.println("=======================================");
        System.out.println("        Java Console Quiz (10 Qs)");
        System.out.println("=======================================");
        System.out.println("Instructions:");
        System.out.println("- Enter A, B, C, or D to answer.");
        System.out.println("- Questions and options are shuffled each run.");
    }

    private int readChoiceIndex(int optionsCount) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < optionsCount; i++) {
            map.put((char)('A' + i), i);
        }

        while (true) {
            System.out.print("Your answer (A-" + (char)('A' + optionsCount - 1) + "): ");
            String input = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            if (input.length() == 1) {
                char c = input.charAt(0);
                switch (c) {
                    case 'A': case 'B': case 'C': case 'D':
                        if (map.containsKey(c)) return map.get(c);
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Invalid input. Please type one of: " + map.keySet());
        }
    }

    private void printResult() {
        int total = questions.size();
        double percent = (score * 100.0) / total;

        System.out.println();
        System.out.println("=======================================");
        System.out.println("                RESULTS                ");
        System.out.println("=======================================");
        System.out.printf(Locale.ROOT, "Score: %d/%d (%.2f%%)%n", score, total, percent);

        String remark;
        if (percent >= 90) remark = "Outstanding! 🎉";
        else if (percent >= 75) remark = "Great job!";
        else if (percent >= 60) remark = "Good effort!";
        else remark = "Keep practicing!";
        System.out.println("Remark: " + remark);
    }

    private void reviewAnswers() {
        System.out.println();
        System.out.println("-------- Review --------");

        Iterator<AnswerRecord> it = history.iterator();
        int i = 1;
        while (it.hasNext()) {
            AnswerRecord ar = it.next();
            Question q = ar.q;
            String correct = q.getOptions().get(q.getCorrectIndex());
            String chosen  = (ar.chosenIndex >= 0) ? q.getOptions().get(ar.chosenIndex) : "(none)";

            System.out.println("Q" + i + ": " + q.getText());
            System.out.println("   Your answer : " + chosen);
            System.out.println("   Correct     : " + correct);
            System.out.println(ar.chosenIndex == q.getCorrectIndex() ? "   ✓ Correct" : "   ✗ Incorrect");
            i++;
        }
    }

    private void promptRetake() {
        System.out.println();
        System.out.print("Retake quiz? (Y/N): ");
        String s = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if ("Y".equals(s)) {
            score = 0;
            history.clear();
            run();
        } else {
            System.out.println("Thanks for playing! 👋");
        }
    }
}
