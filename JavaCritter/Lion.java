/** Lion class for Critter assignment
* @author Bao Trinh
* @version 145 (4/23/2023)
*/

import java.awt.Color;
import java.util.Random;

public class Lion extends Critter{
    private Color color;
    private Random randomColor;
    private int moveCount;

    /**
     * Initializes a new Lion instance with a randomly selected color.
     * @param randomColor this is to make a random class
     * @param color this is to get the results of randomColor
     * @param moveCount this is to count the Lion steps
     */
    public Lion() {
        this.randomColor = new Random();
        this.color = randomColor();
        this.moveCount = 0;
    }

    /**
     * Returns the current color of the Lion.
     *
     * @return The current color of the Lion.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the string representation of the Lion.
     *
     * @return "L".
     */
    public String toString() {
        return "L";
    }

    /**
     * Determines the Lion's next action based on the current state and surroundings.
     *
     * @param this is to determine what action the Lion should do
     * @return The next Action the Lion will take, based on its current state and surroundings.
     */
    public Action getMove(CritterInfo info) {
        if(info.getFront()==Neighbor.OTHER) {
            changeColor();
            return Action.INFECT;
        }
        else if(info.getFront() == Neighbor.WALL || info.getRight()==Neighbor.WALL) {
            changeColor();
            return Action.LEFT;
        }
        else if(info.getFront()== Neighbor.SAME) {
            changeColor();
            return Action.RIGHT;
        }
        else {
            changeColor();
            return Action.HOP;
        }
    }

    /**
     * Generates and returns a random color.
     *
     * @return A randomly selected color.
     */
    private Color randomColor() {
        int threeColor = randomColor.nextInt(3);
        if(threeColor ==0) {
            return Color.RED;
        }
        else if (threeColor ==1) {
            return Color.GREEN;
        }
        else {
            return Color.BLUE;
        }
    }

    /**
     * Changes the color of the Lion every third move.
     */
    private void changeColor() {
        moveCount++;
        if(moveCount%3==0) {
            color = randomColor();
        }
    }

}
