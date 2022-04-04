import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Tokenizer1 {
    public static void main(String[] args) {
        StrTokenizer1 parser = new StrTokenizer1(new ArrayList<>(Arrays.asList("-")));
        parser.readInput("Enter a string: ");
        parser.createTokens();
        parser.printTokens();
    }
}

class StrTokenizer1 {
    private Scanner scanner;
    protected String userInput;
    protected int currentIndex;

    protected HashMap<String, String> operators;
    protected ArrayList<Token> tokens;

    public StrTokenizer1(ArrayList<String> operators) {
        this.scanner = new Scanner(System.in);
        this.userInput = "";
        this.operators = new HashMap<>();
        this.tokens = new ArrayList<>();
        this.currentIndex = 0;

        // initialize reserved strings with token identifiers
        for (String op : operators) {
            this.operators.put(op, op);
            this.operators.put(op, op);
        }
    }

    public void createTokens() {
        if (userInput.length() == 0) {
            return;
        }

        while (true) {
            if (currentIndex >= userInput.length()) {
                break;
            }

            // skip whitespace
            skipWhiteSpace();

            // check for symbols
            if (userInput.charAt(currentIndex) == '=') {
                tokens.add(new Token("Symbol", Character.toString(userInput.charAt(currentIndex))));
            }

            // check for operators
            if (operators.containsKey(Character.toString(userInput.charAt(currentIndex)))) {
                tokens.add(new Token("operator", Character.toString(userInput.charAt(currentIndex))));
            }

            // check for identifiers
            if (Character.isAlphabetic(userInput.charAt(currentIndex))) {
                parseIdentifier();
                continue;
            }

            // move to next char
            currentIndex++;
        }
    }

    protected void skipWhiteSpace() {
        while (userInput.charAt(currentIndex) == '\t' || userInput.charAt(currentIndex) == '\n'
                || userInput.charAt(currentIndex) == '\n') {
            currentIndex++;

            if (currentIndex >= userInput.length()) {
                break;
            }
        }
    }

    protected void parseIdentifier() {
        String str = "";
        while (true) {
            str += userInput.charAt(currentIndex);

            currentIndex++;

            if (currentIndex >= userInput.length()) {
                break;
            }

            // check for symbols
            if (userInput.charAt(currentIndex) == '=') {
                tokens.add(new Token("Symbol", Character.toString(userInput.charAt(currentIndex))));
            }

            // check if operator is next char
            if (operators.containsKey(Character.toString(userInput.charAt(currentIndex)))) {
                break;
            }

            // check for space, tab, or newline
            if (userInput.charAt(currentIndex) == '\t' || userInput.charAt(currentIndex) == '\n'
                    || userInput.charAt(currentIndex) == ' ') {
                break;
            }
        }

        Token token = new Token("identifier", str);
        tokens.add(token);
    }

    public void readInput(String prompt) {
        System.out.println(prompt);
        userInput = scanner.nextLine();
        currentIndex = 0;
    }

    public String getUserInput() {
        return this.userInput;
    }

    public void printTokens() {
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(
                    "TOKEN#" + Integer.toString(i + 1) + " " + tokens.get(i).value + ": " + tokens.get(i).type);
        }

        System.out.println("TOTAL NUMBER OF TOKENS: " + Integer.toString(tokens.size()));
        System.out.println("END OF STRING");
        System.out.println("=====================================");
    }
}
