/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentjxhernandez.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;

/**
 * Class that will store x and y coordinates for a hexagonal hanto board
 */
public class HantoPosition implements HantoCoordinate {

	private int x;
	private int y;

	/**
	 * Constructor for HantoPosition.
	 * 
	 * @param x
	 *            int x-coordinate
	 * @param y
	 *            int y-coordinate
	 */
	public HantoPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x * 17;
		result = prime * result + y * 61;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HantoPosition other = (HantoPosition) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
	
	/**
	 * Method that tests whether a given coordinate is next to this coordinate
	 * @param otherPos Other position to compare
	 * @return True if the coordinate is beside this one, false otherwise.
	 */
	public boolean isNextTo(HantoCoordinate otherPos) {
		int distX = Math.abs(otherPos.getX() - getX());
		int distY = Math.abs(otherPos.getY() - getY());
		// The distance will work except for two hexagonal positions
		// Which are false positives, when subtracted they must not 
		// Equal to (1,1) or (-1,-1) 
		HantoPosition edgePosition1 = new HantoPosition(1, 1);
		HantoPosition edgePosition2 = new HantoPosition(-1, -1);
		HantoPosition difference = new HantoPosition(otherPos.getX() - getX(), otherPos.getY() - getY());
		boolean edgeCase = difference.equals(edgePosition1) || difference.equals(edgePosition2);
		return (!((distX == 0 && distY == 0) || distX > 1 || distY > 1 || edgeCase));
	}

}
