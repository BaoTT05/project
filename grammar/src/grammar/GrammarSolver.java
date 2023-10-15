import java.util.*;
/**
 * @author Bao Trinh
 * @version 1.0
 */
public class GrammarSolver {
    private Map<String, String[]> grammar;

    /**
     * Constructs a new GrammarSolver with the specified grammar rules.
     *
     * @param rules the list of grammar rules
     * @throws IllegalArgumentException if the list is null, empty, or contains duplicate symbols
     */
    public GrammarSolver(List<String> rules) {
        if (rules == null || rules.size() == 0) {
            throw new IllegalArgumentException("Invalid grammar rules");
        }

        grammar = new HashMap<>();

        for (String rule : rules) {
            String[] parts = rule.split("::=");
            String symbol = parts[0].trim();

            if (grammar.containsKey(symbol)) {
                throw new IllegalArgumentException("Duplicate symbol found: " + symbol);
            }

            String[] productions = parts[1].trim().split("[|]");
            grammar.put(symbol, productions);
        }
    }

    /**
     * Checks if the given symbol is a non-terminal in the grammar.
     *
     * @param symbol the symbol to check
     * @return true if the symbol is a non-terminal, false otherwise
     * @throws IllegalArgumentException if the symbol is null or has a length of 0
     */
    public boolean contains(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Invalid symbol");
        }

        return grammar.containsKey(symbol);
    }

    /**
     * Retrieves the set of all non-terminal symbols in the grammar.
     *
     * @return the set of non-terminal symbols as a sorted set of strings
     */
    public Set<String> getSymbols() {
        return new TreeSet<>(grammar.keySet());
    }

    /**
     * Generates a random occurrence of the given symbol in the grammar.
     *
     * @param symbol the symbol to generate
     * @return the generated occurrence of the symbol as a string
     * @throws IllegalArgumentException if the symbol is null or has a length of 0
     */
    public String generate(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        return generateHelper(symbol);
    }

    /**
     * Recursive helper method for generating occurrences of symbols.
     *
     * @param symbol the symbol to generate
     * @return the generated occurrence of the symbol as a string
     */
    private String generateHelper(String symbol) {
        if (!grammar.containsKey(symbol)) {
            return symbol + " ";
        } else {
            String[] productions = grammar.get(symbol);
            String randomProduction = productions[new Random().nextInt(productions.length)];
            String[] value = randomProduction.trim().split("\\s+"); //I had to get rid of symbols like - or space, etc... I google the //s+

            String result = "";
            for (String outValue : value) {
                result += generateHelper(outValue);
            }
            return result;
        }
    }
}
