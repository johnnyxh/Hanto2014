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
import java.util.Map;
import java.util.Map.Entry;

/**
 * The movement validator for the flying move type
 */
public class HantoFly implements HantoMoveValidator {
	
	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest, Map<HantoPosition,Piece> board) {
		boolean isValid = false;
		List<HantoPosition> pieces = new ArrayList<HantoPosition>();
		// Gather a list of friendly and enemy occupied positions on the board
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			if (!entry.getKey().equals(orig)) {
					pieces.add(entry.getKey());
			}
		}
		// Check if the landing is next to a piece but not ontop
		for (int i = 0; i < pieces.size(); i++) {
			if (dest.equals(pieces.get(i))) {
				return false;
			}
			if (dest.isNextTo(pieces.get(i))) {
				isValid = true;
			}
		}
		return isValid;
	}
	
}
