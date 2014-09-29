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

	protected static final HantoPosition CENTER_HEX = new HantoPosition(0, 0);
	protected static final int FIRST_TURN = 0;
	protected static final int SECOND_TURN = 1;

	protected Map<HantoPosition, Piece> board = new HashMap<HantoPosition, Piece>();
	protected List<HantoMoveRule> pieceMoves = new ArrayList<HantoMoveRule>();
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
		Map<HantoPosition, Piece> preMoveBoard = new HashMap<HantoPosition, Piece>(board);
		MoveResult specificRuleResult;

		// Resignation check
		if (pieceType == null && from == null && to == null) {
			if (getPlayerTurn().getPlayerColor() == HantoPlayerColor.RED) {
				return MoveResult.BLUE_WINS;
			} else {
				return MoveResult.RED_WINS;
			}
		}

		// Return draw when out of pieces
		if (getPlayerTurn().getPieceCount() == 0) {
			return MoveResult.DRAW;
		}
		// Check if the specific hanto game has rules that change the outcome of
		// this movement before logic
		specificRuleResult = preRuleSetCheck(pieceType, orig, dest);
		if (specificRuleResult != null) {
			return specificRuleResult;
		}
		// Check if the movement is valid
		if (!isValidMove(orig, dest, pieceType)) {
			throw new HantoException("This is not a valid movement");
		}
		// Check if the destination is valid
		if (!isValidDestination(orig, dest, getPlayerTurn().getPlayerColor())) {
			throw new HantoException("This is not a valid destination");
		}
		// Move/Place the piece
		movePiece(orig, dest, pieceType, getPlayerTurn());
		
		if (!isContiguousBoard()) {
			board.clear();
			board.putAll(preMoveBoard);
			throw new HantoException("This is not a valid board state, move reverted");
		}
		// Check if the specific hanto game has rules that change the outcome of
		// this movement after logic
		specificRuleResult = postRuleSetCheck(pieceType, orig, dest);
		if (specificRuleResult != null) {
			return specificRuleResult;
		}
		// Return the state of the game after the move
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
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
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
	public Player getPlayerTurn() {
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
	protected boolean isValidDestination(HantoPosition orig, HantoPosition to,
			HantoPlayerColor movingColor) {
		boolean isValid = false;

		// Check for first move condition
		if (numTurns == FIRST_TURN && to.equals(CENTER_HEX)) {
			return true;
		}
		// Check for second move condition
		if (numTurns == SECOND_TURN
				&& CENTER_HEX.surroundingHexes().contains(to)) {
			return true;
		}

		List<HantoPosition> friendlyPieces = new ArrayList<HantoPosition>();
		List<HantoPosition> enemyPieces = new ArrayList<HantoPosition>();
		// Gather a list of friendly and enemy occupied positions on the board
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			if (entry.getValue().getColor() == movingColor) {
				// Be sure not to count itself if moving existing piece
				if (!entry.getKey().equals(orig)) {
					friendlyPieces.add(entry.getKey());
				}
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
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
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
			board.remove(orig);
			board.put(dest, new Piece(piece, player.getPlayerColor()));
			numTurns++;
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
			// Only walking 1 square
			/*
			if (orig.surroundingHexes().contains(dest)
					&& getPieceAt(orig).getColor() == getPlayerTurn()
							.getPlayerColor()) {
				List<HantoPosition> walkingSpaceHexes = orig.surroundingHexes();
				walkingSpaceHexes.retainAll(dest.surroundingHexes());
				if (getPieceAt(walkingSpaceHexes.get(0)) == null
						|| getPieceAt(walkingSpaceHexes.get(1)) == null) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
			*/
			if (getPieceAt(orig).getColor() != getPlayerTurn().getPlayerColor()) {
				return false;
			}
			for(int i=0; i < pieceMoves.size(); i++) {
				if (pieceMoves.get(i).getPiece() == pieceType) {
					return pieceMoves.get(i).getMoveValidator().isMoveValid(orig, dest, board);
				}
			}
			return false;
		} else {
			// Its not being moved so check for valid destination
			return true;
		}

	}

	protected boolean hasPlacedButterfly(HantoPlayerColor movingColor) {
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			if (entry.getValue().getType() == HantoPieceType.BUTTERFLY
					&& entry.getValue().getColor() == movingColor) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Specifies if the current board state is contiguous (All pieces are connected)
	 * Top level method for recursive isContiguousBoardR
	 * @return True if the board is contiguous, false otherwise
	 */
	protected boolean isContiguousBoard() {
		HantoPosition currentVisit = null;
		List<HantoPosition> hasVisited = new ArrayList<HantoPosition>();
		List<HantoPosition> toVisit = new ArrayList<HantoPosition>();
		List<HantoPosition> canVisit = new ArrayList<HantoPosition>();
		for (Entry<HantoPosition, Piece> entry : board.entrySet()) {
			toVisit.add(entry.getKey());
		}
		currentVisit = toVisit.get(0);
		return isContiguousBoardR(currentVisit, toVisit, canVisit, hasVisited);
	}

	private boolean isContiguousBoardR(HantoPosition current,
			List<HantoPosition> toVisit, List<HantoPosition> canVisit,
			List<HantoPosition> hasVisited) {
		hasVisited.add(current);
		toVisit.remove(current);
		canVisit.remove(current);
		for (int i = 0; i < current.surroundingHexes().size(); i++) {
			HantoPosition currentAdjacent = current.surroundingHexes().get(i);
			if (getPieceAt(currentAdjacent) != null
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
					hasVisited);
		}
	}

	/**
	 * A method for the use of specific versions of Hanto to override to perform
	 * checks before the general logic of movement is checked for
	 * 
	 * @param pieceType
	 *            The moving piece
	 * @param from
	 *            The original position
	 * @param to
	 *            The destination position
	 * @return A MoveResult if the rules of the specific Hanto game decides an
	 *         outcome before logic comes into play, null otherwise
	 * @throws HantoException
	 *             If the rules of the specific Hanto game are violated in some
	 *             way
	 */
	protected MoveResult preRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		return null;
	}

	/**
	 * A method for the use of specific versions of Hanto to override to perform
	 * checks after the general logic of movement is checked for
	 * 
	 * @param pieceType
	 *            The moving piece
	 * @param from
	 *            The original position
	 * @param to
	 *            The destination position
	 * @return A MoveResult if the rules of the specific Hanto game decides an
	 *         outcome after general logic comes into play, null otherwise
	 * @throws HantoException
	 *             If the rules of the specific Hanto game are violated in some
	 *             way
	 */
	protected MoveResult postRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		return null;
	}

	public int getNumTurns() {
		return numTurns;
	}

	public void setNumTurns(int numTurns) {
		this.numTurns = numTurns;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public Map<HantoPosition, Piece> getBoard() {
		return board;
	}

	public void setBoard(Map<HantoPosition, Piece> board) {
		this.board = board;
	}

}
