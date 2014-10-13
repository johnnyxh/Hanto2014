/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoCoordinate;

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
	 * Method that tests whether a given position is next to this position
	 * 
	 * @param otherPos
	 *            Other position to compare
	 * @return True if the position is beside this one, false otherwise.
	 */
	public boolean isNextTo(HantoCoordinate otherPos) {
		int distX = Math.abs(otherPos.getX() - getX());
		int distY = Math.abs(otherPos.getY() - getY());
		// The distance will work except for two hexagonal positions
		// Which are false positives, when subtracted they must not
		// Be these bottom two coordinates
		HantoPosition edgePosition1 = new HantoPosition(1, 1);
		HantoPosition edgePosition2 = new HantoPosition(-1, -1);
		HantoPosition difference = new HantoPosition(otherPos.getX() - getX(),
				otherPos.getY() - getY());
		boolean edgeCase = difference.equals(edgePosition1)
				|| difference.equals(edgePosition2);
		return (!((distX == 0 && distY == 0) || distX > 1 || distY > 1 || edgeCase));
	}

	/**
	 * Gets the HantoPositions of all hexes surrounding this position
	 * 
	 * @return A List containing all six surrounding hexes
	 */
	public List<HantoPosition> surroundingHexes() {
		List<HantoPosition> hexes = new ArrayList<HantoPosition>();
		// Top hex
		HantoPosition one = new HantoPosition(getX(), getY() + 1);
		hexes.add(one);
		// Top left hex
		HantoPosition two = new HantoPosition(getX() - 1, getY() + 1);
		hexes.add(two);
		// Bottom left hex
		HantoPosition three = new HantoPosition(getX() - 1, getY());
		hexes.add(three);
		// Bottom hex
		HantoPosition four = new HantoPosition(getX(), getY() - 1);
		hexes.add(four);
		// Bottom right hex
		HantoPosition five = new HantoPosition(getX() + 1, getY() - 1);
		hexes.add(five);
		// Top right hex
		HantoPosition six = new HantoPosition(getX() + 1, getY());
		hexes.add(six);

		return hexes;
	}

	/**
	 * Gets the distance between this hanto position and the supplied hanto
	 * position
	 * 
	 * @param to
	 *            The destination position
	 * @return An integer distance
	 */
	public int distanceTo(HantoPosition to) {
		int deltaX = Math.abs(this.getX() - to.getX());
		int deltaY = Math.abs(this.getY() - to.getY());
		int deltaXY = Math.abs((this.getX() - to.getX())
				+ (this.getY() - to.getY()));

		return (deltaX + deltaY + deltaXY) / 2;
	}

	/**
	 * Gets a list of hanto postion that are between this position and the
	 * supplied hanto position in a line
	 * 
	 * @param to
	 *            The destination position
	 * @return A list of hanto positions
	 */
	public List<HantoPosition> hexesBetweenLine(HantoPosition to) {
		List<HantoPosition> hexes = new ArrayList<HantoPosition>();

		int distance = this.distanceTo(to);

		if (this.getX() == to.getX()) {
			// Vertical
			if (this.getY() > to.getY()) {
				// Positive
				for (int i = 1; i < distance; i++) {
					hexes.add(new HantoPosition(this.getX(), this.getY() - i));
				}
			} else {
				// Negative
				for (int i = 1; i < distance; i++) {
					hexes.add(new HantoPosition(this.getX(), this.getY() + i));
				}
			}
		} else if (this.getY() == to.getY()) {
			// Diagonal rising
			if (this.getX() > to.getX()) {
				// Positive
				for (int i = 1; i < distance; i++) {
					hexes.add(new HantoPosition(this.getX() - i, this.getY()));
				}
			} else {
				// Negative
				for (int i = 1; i < distance; i++) {
					hexes.add(new HantoPosition(this.getX() + i, this.getY()));
				}
			}
		} else {
			// Diagonal dropping
			if ((this.getX() + distance) == to.getX()
					&& (this.getY() - distance) == to.getY()) {
				// Positive
				for (int i = 1; i < distance; i++) {
					hexes.add(new HantoPosition(this.getX() + i, this.getY()
							- i));
				}
			} else if ((this.getX() - distance) == to.getX()
					&& (this.getY() + distance) == to.getY()) {
				// Negative
				for (int i = 1; i < distance; i++) {
					hexes.add(new HantoPosition(this.getX() - i, this.getY()
							+ i));
				}
			}
		}
		return hexes;
	}

	/**
	 * Converts a HantoCoordinate into a HantoPosition
	 * 
	 * @param coord
	 *            HantoCoordinate to convert
	 * @return A new HantoPosition object
	 */
	public static HantoPosition coordinateToPosition(HantoCoordinate coord) {
		if (coord == null) {
			return null;
		}
		return new HantoPosition(coord.getX(), coord.getY());
	}

}
