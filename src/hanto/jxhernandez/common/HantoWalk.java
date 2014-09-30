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

import java.util.List;
import java.util.Map;

/**
 * The movement validator for the walk move type
 */
public class HantoWalk implements HantoMoveValidator {

	int numMoves;

	/**
	 * Constructor for HantoWalk.
	 * @param numMoves int
	 */
	public HantoWalk(int numMoves) {
		this.numMoves = numMoves;
	}

	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest,
			Map<HantoPosition, Piece> board) {
		if (orig.surroundingHexes().contains(dest)) {
			List<HantoPosition> walkingSpaceHexes = orig.surroundingHexes();
			walkingSpaceHexes.retainAll(dest.surroundingHexes());
			return board.get(walkingSpaceHexes.get(0)) == null
					|| board.get(walkingSpaceHexes.get(1)) == null;
		}
		return false;
	}

}
