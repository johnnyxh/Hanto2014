/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.beta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoPosition;
import hanto.jxhernandez.common.Piece;

/**
 * Hanto game where each side only gets one butterfly and five sparrows. Pieces
 * can only be placed on the board adjacent to another piece. Except for the
 * first piece which always is placed at (0,0). The game ends in a draw when
 * both players run out of pieces, or when a butterfly is surrounded
 */
public class BetaHantoGame extends HantoBaseGame {

	/**
	 * Constructor for BetaHantoGame.
	 * 
	 * @param movesFirst
	 *            HantoPlayerColor Player color that will move first
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		// Initialize inventories
		firstPlayer.addToReserve(HantoPieceType.SPARROW, 5);
		firstPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		secondPlayer.addToReserve(HantoPieceType.SPARROW, 5);
		secondPlayer.addToReserve(HantoPieceType.BUTTERFLY);
	}

	// In this version of hanto a valid destination is next to both friendly and
	// enemy pieces
	@Override
	protected boolean isValidDestination(HantoPosition orig, HantoPosition to,
			HantoPlayerColor movingColor) {
		boolean isValid = false;

		if (numTurns == FIRST_TURN
				&& to.equals(CENTER_HEX)) {
			return true;
		}

		List<HantoPosition> friendlyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> enemyPieces = new ArrayList<HantoPosition>();
		// Gather a list of friendly and enemy occupied positions on the board
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			if (entry.getValue().getColor() == movingColor) {
				friendlyPieces.add(entry.getKey());
			} else {
				enemyPieces.add(entry.getKey());
			}
		}
		// Check if the selected movement is next to or ontop of an enemy piece
		for (int i = 0; i < enemyPieces.size(); i++) {
			if (to.equals(enemyPieces.get(i))) {
				return false;
			}
			if (to.isNextTo(enemyPieces.get(i))) {
				isValid = true;
			}
		}
		// Check if the selected movement is next to or ontop of a friendly
		// piece
		// Needs to be next to atleast one (and not on top of another friendly)
		for (int i = 0; i < friendlyPieces.size(); i++) {
			if (to.equals(friendlyPieces.get(i))) {
				return false;
			}
			if (to.isNextTo(friendlyPieces.get(i))) {
				isValid = true;
			}
		}
		return isValid;
	}

	@Override
	protected MoveResult preRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		if (from != null) {
			throw new HantoException("Illegal Move: can't move pieces");
		}
		
		// Return draw when out of pieces
		if (getPlayerTurn().getPieceCount() == 0) {
			return MoveResult.DRAW;
		}

		return null;
	}

}
