import java.util.*;

/**
 * @author Bao Trinh
 * @version CS 145
 * Assassin Manager Assignment
 * 6/6/2023
 */
public class AssassinManager {
    private AssassinNode frontKillRing; // Reference to the front of the kill ring
    private AssassinNode frontGraveyard; // Reference to the front of the graveyard

    /**
     * Constructs an AssassinManager object with the provided list of names. 
     * The first name in the list becomes the front of the kill ring, and the 
     * subsequent names are added to the kill ring in the order they appear in 
     * the list.
     *
     * @param names a List of names to initialize the kill ring with
     * @throws IllegalArgumentException if the list of names is empty
     */
    public AssassinManager(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("List of names cannot be empty.");
        }

        frontKillRing = new AssassinNode(names.get(0));

        AssassinNode current = frontKillRing;
        for (int i = 1; i < names.size(); i++) {
            current.next = new AssassinNode(names.get(i));
            current = current.next;
        }
    }

    /**
     * Prints the names of people in the kill ring along with the name of the 
     * person they are stalking.
     */
    public void printKillRing() {
        AssassinNode current = frontKillRing;

        while (current != null) {
            AssassinNode nextNode;
            if (current.next != null) {
                nextNode = current.next;
            } else {
                nextNode = frontKillRing;
            }
            System.out.println("    " + current.name + " is stalking " + nextNode.name);
            current = current.next;
        }
    }

    /**
     * Prints the names of people in the graveyard along with the name of the 
     * person who killed them.
     */
    public void printGraveyard() {
        AssassinNode current = frontGraveyard;
        while (current != null) {
            System.out.println("    " + current.name + " was killed by " + current.killer);
            current = current.next;
        }
    }

    /**
     * Checks if the given name is present in the current kill ring.
     *
     * @param name the name to search for
     * @return true if the name is found in the kill ring, false otherwise
     */
    public boolean killRingContains(String name) {
        AssassinNode current = frontKillRing;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the given name is present in the current graveyard.
     *
     * @param name the name to search for
     * @return true if the name is found in the graveyard, false otherwise
     */
    public boolean graveyardContains(String name) {
        AssassinNode current = frontGraveyard;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the game is over. The game is considered over if there is only 
     * one person left in the kill ring.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean gameOver() {
        return frontKillRing.next == null;
    }

    /**
     * Returns the name of the winner of the game. The winner is determined when 
     * the game is over (i.e., there is only one person left in the kill ring).
     *
     * @return the name of the winner, or null if the game is not over yet
     */
    public String winner() {
        if (!gameOver()) {
            return null;
        }
        return frontKillRing.name;
    }

    /**
     * Records the killing of the person with the given name. The killed person 
     * is moved from the kill ring to the graveyard, and the killer's name is 
     * recorded.
     *
     * @param name the name of the person to kill
     * @throws IllegalStateException if the game is already over
     * @throws IllegalArgumentException if the given name is unknown
     */
    public void kill(String name) {
        if (gameOver()) {
            throw new IllegalStateException("The game is already over.");
        }

        AssassinNode current = frontKillRing;
        AssassinNode previous = null;

        while (current != null && !current.name.equalsIgnoreCase(name)) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            throw new IllegalArgumentException("Unknown person.");
        }

        if (previous == null) {
            frontKillRing = current.next;
            current.killer = frontKillRing.name;
        } else {
            previous.next = current.next;
            current.killer = previous.name;
        }

        current.next = frontGraveyard;
        frontGraveyard = current;
    }
}