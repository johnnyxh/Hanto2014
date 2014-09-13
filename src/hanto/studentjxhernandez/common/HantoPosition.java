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

/**
 * Class that will store x and y coordinates for a hexagonal hanto board
 */
public class HantoPosition implements HantoCoordinate {
	
	private int x;
	private int y;
	
	/**
	 * Constructor for HantoPosition.
	 * @param x int x-coordinate
	 * @param y int y-coordinate
	 */
	public HantoPosition (int x, int y) {
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

}
