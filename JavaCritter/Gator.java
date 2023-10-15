/** Gator class for Critter assignment
* @author Bao Trinh
* @version 145 (4/23/2023)
*/

import java.awt.Color;

/**
 * my Gator will works like this:
 * 1. It updates its hunger status to true after 10 steps without seeing an enemy.
 * 2. If it encounters an enemy, it infects it.
 * 3. If it encounters a wall or another Gator in front, it turns left.
 * 4. If it encounters an enemy on its left or right, it turns towards the enemy.
 * 5. If none of the above conditions are met, it hops forward.
 */
public class Gator extends Critter {
    /**
     * A boolean show the hunger state of the Gator.
     */
    private boolean hungry;

    /**
     * this will representing the number of steps since the Gator last saw an enemy.
     */
    private int lastSeenEnemy;

    /**
     * Initializes the Gator with the default state.
     * @param hungry this is keep in check if gator is hungry or not, a status.
     * @param lastSeenEnemy this is count last time gator gets near enemy.
     */
    public Gator() {
        this.hungry = false;
        this.lastSeenEnemy = -1;
    }

    /**
     * Returns the color of the Gator.
     *
     * @return Color.GREEN, representing the color of the Gator.
     */
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * Returns the string representation of the Gator.
     *
     * @return "G[h]" if the Gator is hungry, "G" otherwise.
     */
    public String toString() {
        if (this.hungry) {
            return "G[h]";
        } else {
            return "G";
        }
    }

    /**
     * Determines the Gator's next action based on the current state and surroundings.
     *
     * @param info CritterInfo object providing information about the Gator's surroundings.
     * @return The next Action the Gator will take, based on its current state and surroundings.
     */
    public Action getMove(CritterInfo info) {

        if (info.getFront() == Neighbor.OTHER || info.getBack() == Neighbor.OTHER || info.getLeft() == Neighbor.OTHER || info.getRight() == Neighbor.OTHER) {  //this is to check if gator get close to any enemy
            this.hungry = false;
            this.lastSeenEnemy = 0;
        } else {
            this.lastSeenEnemy++;
        }

        if (this.lastSeenEnemy >= 9) { //this will update
            this.hungry = true;
        } else {
            this.hungry = false;
        }

        if (info.getFront() == Neighbor.OTHER) { // the rest of this will show how the gator will act
            return Action.INFECT;
        } 
        else if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME) {
            return Action.LEFT;
        } 
        else if (info.getRight() == Neighbor.OTHER) {
            return Action.LEFT;
        } 
        else if (info.getLeft() == Neighbor.OTHER) {
            return Action.RIGHT;
        } 
        else {
            return Action.HOP;
        }
    }
}
