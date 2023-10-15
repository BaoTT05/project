/** Bear class for Critter assignment
* @author Bao Trinh
* @version 145 (4/23/2023)
*/

import java.awt.Color;

public class Bear extends Critter {
	private boolean polar = true;
	private boolean slashed = true;
	
	/**
	 * Constructs a Bear object with the given polar status.
	 * 
	 * @param polar true if the Bear is polar, false otherwise
	 * @param Slashed turn alternate slashes
	 */
	public Bear(boolean polar) {
		this.polar = polar;
		this.slashed = true;
	}



	/**
	 * Returns the color of the Bear.
	 * 
	 * @return Color.WHITE if the Bear is polar, Color.BLACK otherwise
	 */
	public Color getColor() {
		if(polar) {
			return Color.WHITE;
		}
		else {
			return Color.BLACK;
		}
	}


	/**
	 * Returns the string representation of the Bear.
	 * The Bear alternates between "/" and "\" every other turn.
	 * 
	 * @return "/" if the Bear is on a slashed turn, "\" otherwise
	 */
	public String toString() {
		if(slashed) {
			slashed = false;
			return "/";
		}
		else {
			slashed = true;
			return "\\";
		}
	}

	/**
	 * Determines the Bear's next action based on the current state and surroundings.
	 * @return the next Action the Bear will take, based on its current state and surroundings
	 */
	public Action getMove(CritterInfo info) {
		if(info.getFront()==Neighbor.OTHER) {
			return Action.INFECT;
		}
		else if(info.getFront() == Neighbor.EMPTY) {
			return Action.HOP;
		}
		else {
			return Action.LEFT;
		}
	}

}
