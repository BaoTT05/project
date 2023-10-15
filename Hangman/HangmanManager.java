import java.util.*;
public class HangmanManager {
    private Set<String> words;
    private Set<Character> guesses;
    private String pattern;
    private int guessesLeft;

    public HangmanManager(Collection<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException("Invalid length or max");
        }
        words = new TreeSet<>();
        guesses = new TreeSet<>();
        pattern = "";
        guessesLeft = max;
        for (String word : dictionary) {
            if (word.length() == length) {
                words.add(word);
            }
        }
        if (words.isEmpty()) {
            throw new IllegalArgumentException("No words of given length in the dictionary");
        }
        for (int i = 0; i < length; i++) {
            pattern += "-";
        }
    }
    public Set<String> words() {
        return words;
    }
    public int guessesLeft() {
        return guessesLeft;
    }
    public Set<Character> guesses() {
        return guesses;
    }
    public String pattern() {
        if (words.isEmpty()) {
            throw new IllegalStateException("No words remaining");
        }
        return pattern;
    }
    public int record(char guess) {
        if (guessesLeft < 1 || words.isEmpty()) {
            throw new IllegalStateException("No guesses left or no words remaining");
        }
        if (guesses.contains(guess)) {
            throw new IllegalArgumentException("Guess already made");
        }

        guesses.add(guess);
        Map<String, Set<String>> patternToWords = new HashMap<>();

        for (String word : words) {
            String wordPattern = getPattern(word, guess);
            Set<String> patternWords = patternToWords.get(wordPattern);
            if (patternWords == null) {
                patternWords = new TreeSet<>();
                patternToWords.put(wordPattern, patternWords);
            }
            patternWords.add(word);
        }

        String largestPattern = "";
        int largestPatternSize = 0;

        for (String currentPattern : patternToWords.keySet()) {
            Set<String> currentWords = patternToWords.get(currentPattern);

            if (currentWords.size() > largestPatternSize) {
                largestPattern = currentPattern;
                largestPatternSize = currentWords.size();
            }
        }

        if (largestPattern.equals(pattern)) {
            guessesLeft--;
        }

        pattern = largestPattern;
        words = patternToWords.get(pattern);

        return countOccurrences(pattern, guess);
    }

    private String getPattern(String word, char guess) {
    	String idek = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                idek +=guess;
            } else {
                idek += pattern.charAt(i);
            }
        }
        return idek;
    }
    private int countOccurrences(String pattern, char guess) {
        int count = 0;
        for (char c : pattern.toCharArray()) {
            if (c == guess) {
                count++;
            }
        }
        return count;
    }
}