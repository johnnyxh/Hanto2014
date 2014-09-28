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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

public abstract class HantoBaseGame implements HantoGame {

	protected static final int HANTO_CENTER_X = 0;
	protected static final int HANTO_CENTER_Y = 0;
	protected static final int FIRST_TURN = 0;

	protected Map<HantoPosition, HantoPiece> board = new HashMap<HantoPosition, HantoPiece>();
	protected int numTurns;
	protected Player firstPlayer;
	protected Player secondPlayer;

	public HantoBaseGame(HantoPlayerColor movesFirst) {
		numTurns = 0;
		this.firstPlayer = new Player(movesFirst);
		if (movesFirst == HantoPlayerColor.RED) {
			secondPlayer = new Player(HantoPlayerColor.BLUE);
		} else {
			secondPlayer = new Player(HantoPlayerColor.RED);
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {

		HantoPosition orig = HantoPosition.coordinateToPosition(from);
		HantoPosition dest = HantoPosition.coordinateToPosition(to);
		
		// Return draw when out of pieces
		if (getPlayerTurn().getPieceCount() == 0) {
			return MoveResult.DRAW;
		}
		
		preRuleSetCheck(pieceType, orig, dest);

		if (!isValidMove(orig, dest, pieceType)) {
			throw new HantoException("This is not a valid movement");
		}
		if (!isValidDestination(dest, getPlayerTurn().getPlayerColor())) {
			throw new HantoException("This is not a valid destination");
		}
		movePiece(orig, dest, pieceType, getPlayerTurn());
		return checkGame();
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		HantoCoordinate dest = HantoPosition.coordinateToPosition(where);
		return board.get(dest);
	}

	@Override
	public String getPrintableBoard() {
		StringBuilder printBoard = new StringBuilder();
		for (Entry<HantoPosition, HantoPiece> entry : board.entrySet()) {
			printBoard
					.append(entry.getValue() + " AT " + entry.getKey() + "\n");
		}
		return printBoard.toString();
	}

	/**
	 * Gets the current player's turn
	 * 
	 * @return A HantoPlayerColor of the current player's turn
	 */
	protected Player getPlayerTurn() {
		if (numTurns % 2 == 0) {
			return firstPlayer;
		} else {
			return secondPlayer;
		}
	}

	/**
	 * Checks if the destination position is valid for this piece
	 * 
	 * @param to
	 *            Destination position
	 * @param movingColor
	 *            The color of the moving player
	 * @return True if the destination is valid, false otherwise
	 */
	protected boolean isValidDestination(HantoPosition to,
			HantoPlayerColor movingColor) {
		boolean isValid = false;

		// Check for first move condition
		if (numTurns == FIRST_TURN
				&& (to.getX() == HANTO_CENTER_X && to.getY() == HANTO_CENTER_Y)) {
			return true;
		}

		List<HantoPosition> friendlyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> enemyPieces = new ArrayList<HantoPosition>();
		// Gather a list of friendly and enemy occupied positions on the board
		for (Entry<HantoPosition, HantoPiece> entry : board.entrySet()) {
			if (entry.getValue().getColor() == movingColor) {
				friendlyPieces.add(entry.getKey());
			} else {
				enemyPieces.add(entry.getKey());
			}
		}
		// Check if the selected movement is next to or ontop of an enemy piece
		for (int i = 0; i < enemyPieces.size(); i++) {
			if (to.equals(enemyPieces.get(i))
					|| to.isNextTo(enemyPieces.get(i))) {
				return false;
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

	protected MoveResult checkGame() {
		boolean redLoses = false;
		boolean blueLoses = false;
		HantoPosition redButterfly = null;
		HantoPosition blueButterfly = null;
		for (Entry<HantoPosition, HantoPiece> entry : board.entrySet()) {
			// Has a butterfly been found?
			if (entry.getValue().getType() == HantoPieceType.BUTTERFLY) {
				// Store the respective butterflies into special variables for
				// later and set lose to true as there is now the possibility of
				// a loss
				if (entry.getValue().getColor() == HantoPlayerColor.BLUE) {
					blueButterfly = entry.getKey();
					blueLoses = true;
				} else {
					redButterfly = entry.getKey();
					redLoses = true;
				}
			}
		}
		// If butterfly hasn't been placed red can't really lose right now
		if (redButterfly != null) {
			List<HantoPosition> surroundingRed = redButterfly
					.surroundingHexes();
			for (int i = 0; i < surroundingRed.size(); i++) {
				// If atleast one hex surrounding it is empty red hasn't lost
				if (board.get(surroundingRed.get(i)) == null) {
					redLoses = false;
					break;
				}
			}
		}
		// If butterfly hasn't been placed blue can't really lose right now
		if (blueButterfly != null) {
			List<HantoPosition> surroundingBlue = blueButterfly
					.surroundingHexes();
			for (int i = 0; i < surroundingBlue.size(); i++) {
				// If atleast one hex surrounding it is empty blue hasn't lost
				if (board.get(surroundingBlue.get(i)) == null) {
					blueLoses = false;
					break;
				}
			}
		}
		// Check against all scenarios including first players 7th turn which
		// means he's out of pieces and loses
		if (redLoses && blueLoses) {
			return MoveResult.DRAW;
		} else if (redLoses) {
			return MoveResult.BLUE_WINS;
		} else if (blueLoses) {
			return MoveResult.RED_WINS;
		} else {
			return MoveResult.OK;
		}
	}

	protected void movePiece(HantoPosition orig, HantoPosition dest,
			HantoPieceType piece, Player player) throws HantoException {
		if (orig != null) {
			// Change the piece at position and move it to new position
		} else {
			// This will throw an exception if the piece isn't in reserve
			player.removeFromReserve(piece);
			board.put(dest, new Piece(piece, player.getPlayerColor()));
			numTurns++;
		}
	}

	protected boolean isValidMove(HantoPosition orig, HantoPosition dest,
			HantoPieceType pieceType) {
		if (orig != null) {
			// Check if the move is valid
			return false;
		} else {
			// Its not being moved so check for valid destination
			return true;
		}

	}

	protected boolean hasPlacedButterfly(HantoPlayerColor movingColor) {
		for (Entry<HantoPosition, HantoPiece> entry : board.entrySet()) {
			if (entry.getValue().getType() == HantoPieceType.BUTTERFLY
					&& entry.getValue().getColor() == movingColor) {
				return true;
			}
		}
		return false;
	}

	protected void preRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		// Method for specific games to implement
	}

	protected void postRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		// Method for specific games to implement
	}

}
