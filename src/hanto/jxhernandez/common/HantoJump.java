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
 * Move validator for the hanto jump movement
 */
public class HantoJump implements HantoMoveValidator {

	int numMoves;
	
	/**
	 * Constructor for HantoJump.
	 * @param numMoves int
	 */
	public HantoJump(int numMoves) {
		this.numMoves = numMoves;
	}
	
	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest,
			Map<HantoPosition, Piece> board) {
		List<HantoPosition> hexLine = orig.hexesBetweenLine(dest);
		// No spaces being jumped, needs to jump over at least one (or not going in straight line so returned empty)
		if (hexLine.size() == 0) {
			return false;
		}
		// If there isn't a piece return false;
		for(int i=0; i < hexLine.size(); i++) {
			if (board.get(hexLine.get(i)) == null) {
				return false;
			}
		}
		return true;
	}

}
