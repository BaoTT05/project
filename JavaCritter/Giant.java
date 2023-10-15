/** Giant class for Critter assignment
* @author Bao Trinh
* @version 145 (4/23/2023)
*/

import java.awt.Color;


public class Giant extends Critter {
	/** this will count the moves */
    private int moves;
    /** this is the counter for the every 6 steps */
    private int counter;

    /**
     * Constructs a new Giant critter with initial values for moves and counter.
     * @param moves how many time giant moves, start at 0.
     * @param counter cycle of every 6 moves
     */
    public Giant() {
        this.moves = 0;
        this.counter = 0;
    }

    /**
     * Returns the color of the Giant critter.
     *
     * @return A Color object representing the color gray.
     */
    public Color getColor() {
        return Color.GRAY;
    }

    /**
     * Returns a string representation of the Giant critter's state.
     *
     * @return A string representing the Giant's noise, one of "fee", "fie", "foe", or "fum".
     */
    public String toString() {
        return giantNoise();
    }

    /**
     * Determines the next move for the Giant critter based on the information provided.
     *
     * @param info A CritterInfo object containing information about the critter's surroundings.
     * @return The next Action the Giant critter should take.
     */
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }

    /**
     * make the sounds for the Giant critter based on its current state.
     *
     * @return A string representing the Giant's noise, one of "fee", "fie", "foe", or "fum".
     */
    private String giantNoise() {
        this.giantMovement();
        if (counter == 0) {
            moves++;
            return "fee";
        } else if (counter == 1) {
            moves++;
            return "fie";
        } else if (counter == 2) {
            moves++;
            return "foe";
        } else {
            moves++;
            return "fum";
        }
    }

    /**
     * Updates the Giant critter's movement counter and noise counter.
     *
     * @return null.
     */
    private String giantMovement() { //an err
        if (moves % 6 == 0 && moves != 0) {
            counter = counter + 1;
        }
        if (counter == 4) {
            counter = 0;
        }
        return null;
    }
}
