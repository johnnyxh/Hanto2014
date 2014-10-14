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

import hanto.tournament.HantoMoveRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class responsible for containing methods to generate possible moves given the
 * board state, a player and his reserves, and the move rule set for pieces
 */
public class PossibleMoves {

	/**
	 * Method movesLeft.
	 * 
	 * @param board
	 *            Map<HantoPosition,Piece>
	 * @param movingPlayer
	 *            Player
	 * @param numTurns
	 *            int
	 * @param moveRules
	 *            List<HantoMoveRule>
	 * @return boolean
	 */
	public static boolean movesLeft(Map<HantoPosition, Piece> board,
			Player movingPlayer, int numTurns, List<HantoMoveRule> moveRules) {
		// Always an option on each players first movement
		if (numTurns <= 1) {
			return true;
		}

		List<HantoPosition> friendlyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> enemyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> allPieces = new ArrayList<HantoPosition>();
		// Gather a list of friendly and enemy occupied positions on the board
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			if (entry.getValue().getColor() == movingPlayer.getPlayerColor()) {
				friendlyPieces.add(entry.getKey());
				allPieces.add(entry.getKey());
			} else {
				enemyPieces.add(entry.getKey());
				allPieces.add(entry.getKey());
			}
		}

		// Check if pieces in inventory and if they can be placed
		if (movingPlayer.getPieceCount() > 0) {
			// Loop through friendly pieces
			for (int i = 0; i < friendlyPieces.size(); i++) {
				List<HantoPosition> possiblePlacements = friendlyPieces.get(i)
						.surroundingHexes();
				// Loop through surrounding positions of a friendly piece
				for (int j = 0; j < possiblePlacements.size(); j++) {
					if (canPlaceThere(board, possiblePlacements.get(j),
							enemyPieces)) {
						return true;
					}
				}
			}
		}
		// Check if pieces can be moved
		for (int i = 0; i < friendlyPieces.size(); i++) {
			HantoPosition movingPosition = friendlyPieces.get(i);
			Piece movingPiece = board.get(movingPosition);
			HantoMoveValidator moveValidator = null;
			// Get the move validator for this piece
			for (int x = 0; x < moveRules.size(); x++) {
				if (moveRules.get(x).getPiece() == movingPiece.getType()) {
					moveValidator = moveRules.get(x).getMoveValidator();
				}
			}
			// Check for other pieces on the board
			for (int j = 0; j < allPieces.size(); j++) {
				List<HantoPosition> possiblePlacements = allPieces.get(j)
						.surroundingHexes();
				// Check if the piece can go in any of these hexes
				for (int k = 0; k < possiblePlacements.size(); k++) {
					if (moveValidator != null
							&& moveValidator.isMoveValid(movingPosition,
									possiblePlacements.get(k), board)
							&& contiguousAfterMove(movingPosition,
									possiblePlacements.get(k), board)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static boolean canPlaceThere(Map<HantoPosition, Piece> board,
			HantoPosition placeToPut, List<HantoPosition> enemyPieces) {
		// if this spot isnt taken
		if (board.get(placeToPut) == null) {
			List<HantoPosition> surroundingPossible = placeToPut
					.surroundingHexes();
			// Check the surrounding for enemy pieces, if none you can place a
			// piece here
			for (int k = 0; k < surroundingPossible.size(); k++) {
				if (enemyPieces.contains(surroundingPossible.get(k))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	private static boolean contiguousAfterMove(HantoPosition orig,
			HantoPosition dest, Map<HantoPosition, Piece> board) {
		if (board.get(dest) != null) {
			return false;
		}
		Map<HantoPosition, Piece> temporaryBoard = new HashMap<HantoPosition, Piece>(
				board);
		Piece movingPiece = temporaryBoard.get(orig);
		temporaryBoard.remove(orig);
		temporaryBoard.put(dest, movingPiece);
		return isContiguousBoard(temporaryBoard);
	}

	/**
	 * Specifies if the current board state is contiguous (All pieces are
	 * connected) Top level method for recursive isContiguousBoardR
	 * 
	 * @return True if the board is contiguous, false otherwise
	 */
	private static boolean isContiguousBoard(Map<HantoPosition, Piece> board) {
		HantoPosition currentVisit = null;
		List<HantoPosition> hasVisited = new ArrayList<HantoPosition>();
		List<HantoPosition> toVisit = new ArrayList<HantoPosition>();
		List<HantoPosition> canVisit = new ArrayList<HantoPosition>();
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			toVisit.add(entry.getKey());
		}
		currentVisit = toVisit.get(0);
		return isContiguousBoardR(currentVisit, toVisit, canVisit, hasVisited,
				board);
	}

	private static boolean isContiguousBoardR(HantoPosition current,
			List<HantoPosition> toVisit, List<HantoPosition> canVisit,
			List<HantoPosition> hasVisited, Map<HantoPosition, Piece> board) {
		hasVisited.add(current);
		toVisit.remove(current);
		canVisit.remove(current);
		for (int i = 0; i < current.surroundingHexes().size(); i++) {
			HantoPosition currentAdjacent = current.surroundingHexes().get(i);
			if (board.get(currentAdjacent) != null
					&& !hasVisited.contains(currentAdjacent)
					&& !canVisit.contains(currentAdjacent)) {
				canVisit.add(currentAdjacent);
			}
		}
		if (toVisit.isEmpty()) {
			return true;
		} else if (canVisit.isEmpty() && !toVisit.isEmpty()) {
			return false;
		} else {
			return isContiguousBoardR(canVisit.get(0), toVisit, canVisit,
					hasVisited, board);
		}
	}

	public static List<HantoMoveRecord> listPossibleMoves(
			Map<HantoPosition, Piece> board, Player movingPlayer, int numTurns,
			List<HantoMoveRule> moveRules) {

		List<HantoMoveRecord> possibleMoves = new ArrayList<HantoMoveRecord>();
		List<HantoPosition> friendlyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> enemyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> allPieces = new ArrayList<HantoPosition>();
		// Gather a list of friendly and enemy occupied positions on the board
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			if (entry.getValue().getColor() == movingPlayer.getPlayerColor()) {
				friendlyPieces.add(entry.getKey());
				allPieces.add(entry.getKey());
			} else {
				enemyPieces.add(entry.getKey());
				allPieces.add(entry.getKey());
			}
		}

		// Check if pieces in inventory and if they can be placed
		if (movingPlayer.getPieceCount() > 0) {
			// Loop through friendly pieces
			for (int i = 0; i < friendlyPieces.size(); i++) {
				List<HantoPosition> possiblePlacements = friendlyPieces.get(i)
						.surroundingHexes();
				// Loop through surrounding positions of a friendly piece
				for (int j = 0; j < possiblePlacements.size(); j++) {
					if (canPlaceThere(board, possiblePlacements.get(j),
							enemyPieces)) {
						// Pick a random piece in the inventory and place it
						// here since it can
						possibleMoves.add(new HantoMoveRecord(movingPlayer
								.reachAndGrab(), null, possiblePlacements
								.get(j)));
					}
				}
			}
		}
		// Check if pieces can be moved
		for (int i = 0; i < friendlyPieces.size(); i++) {
			HantoPosition movingPosition = friendlyPieces.get(i);
			Piece movingPiece = board.get(movingPosition);
			HantoMoveValidator moveValidator = null;
			// Get the move validator for this piece
			for (int x = 0; x < moveRules.size(); x++) {
				if (moveRules.get(x).getPiece() == movingPiece.getType()) {
					moveValidator = moveRules.get(x).getMoveValidator();
				}
			}
			// Check for other pieces on the board
			for (int j = 0; j < allPieces.size(); j++) {
				List<HantoPosition> possiblePlacements = allPieces.get(j)
						.surroundingHexes();
				// Check if the piece can go in any of these hexes
				for (int k = 0; k < possiblePlacements.size(); k++) {
					if (moveValidator != null
							&& moveValidator.isMoveValid(movingPosition,
									possiblePlacements.get(k), board)
							&& contiguousAfterMove(movingPosition,
									possiblePlacements.get(k), board)) {
						possibleMoves.add(new HantoMoveRecord(movingPiece
								.getType(), movingPosition, possiblePlacements
								.get(k)));
					}
				}
			}
		}

		return possibleMoves;
	}

}
