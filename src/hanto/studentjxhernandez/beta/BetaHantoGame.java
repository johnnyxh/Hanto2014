package hanto.studentjxhernandez.beta;

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
import hanto.studentjxhernandez.common.HantoPosition;
import hanto.studentjxhernandez.common.Piece;

public class BetaHantoGame implements HantoGame {

	private static final int FOURTH_TURN_FIRST_P = 6;
	private static final int FOURTH_TURN_SECOND_P = 7;
	private static final int SEVENTH_TURN_FIRST_P = 12;
	private static final int FIRST_TURN_FIRST_P = 0;
	private static final int HANTO_CENTER_X = 0;
	private static final int HANTO_CENTER_Y = 0;

	private Map<HantoPosition, HantoPiece> board = new HashMap<HantoPosition, HantoPiece>();
	private int numTurns;
	private HantoPlayerColor movesFirst;
	private HantoPlayerColor movesSecond;

	public BetaHantoGame(HantoPlayerColor movesFirst) {
		numTurns = 0;
		this.movesFirst = movesFirst;
		if (movesFirst == HantoPlayerColor.RED) {
			movesSecond = HantoPlayerColor.BLUE;
		} else {
			movesSecond = HantoPlayerColor.RED;
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {

		HantoPosition dest = newCords(to.getX(), to.getY());

		if (from != null) {
			throw new HantoException("Illegal Move: can't move pieces");
		}
		if (pieceType == HantoPieceType.BUTTERFLY && hasPlacedButterfly(getPlayerTurn())) {
			throw new HantoException("Illegal Move: only have 1 butterfly");
		}
		// First move of the game, second move of the game, then every other
		if (numTurns == FIRST_TURN_FIRST_P
				&& (to.getX() != HANTO_CENTER_X || to.getY() != HANTO_CENTER_Y)) {
			throw new HantoException("Illegal move: not in center");
		} else if (numTurns > FIRST_TURN_FIRST_P
				&& !isValidMove(dest, getPlayerTurn())) {
			throw new HantoException("Illegal Move: not adjacent to piece");
		} else if (numTurns == FOURTH_TURN_FIRST_P
				|| numTurns == FOURTH_TURN_SECOND_P) {
			if (!hasPlacedButterfly(getPlayerTurn())
					&& pieceType != HantoPieceType.BUTTERFLY) {
				throw new HantoException(
						"Illegal move: hasn't placed butterfly by 4th turn");
			}
		}
		board.put(dest, new Piece(pieceType, getPlayerTurn()));
		numTurns++;
		return moveResult();
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		HantoCoordinate dest = newCords(where.getX(), where.getY());
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

	private HantoPosition newCords(int x, int y) {
		return new HantoPosition(x, y);
	}

	private HantoPlayerColor getPlayerTurn() {
		if (numTurns % 2 == 0) {
			return movesFirst;
		} else {
			return movesSecond;
		}
	}

	// I somewhat jumped the gun when making this method. It implemented the
	// hanto
	// Placement rules (cant be next to enemy pieces, must be next to one
	// friendly
	// piece.) That's why this method is a lot more complex than one for loop.
	// I changed the tests to reflect this.
	private boolean isValidMove(HantoPosition to, HantoPlayerColor movingColor) {
		boolean isValid = false;
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

	private MoveResult moveResult() {
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
		if (redLoses) {
			return MoveResult.BLUE_WINS;
		} else if (blueLoses) {
			return MoveResult.RED_WINS;
		} else if (numTurns - 1 == SEVENTH_TURN_FIRST_P) {
			switch (movesFirst) {
			case RED:
				return MoveResult.BLUE_WINS;
			default:
				return MoveResult.RED_WINS;
			}
		} else {
			return MoveResult.OK;
		}
	}

	private boolean hasPlacedButterfly(HantoPlayerColor movingColor) {
		for (Entry<HantoPosition, HantoPiece> entry : board.entrySet()) {
			if (entry.getValue().getType() == HantoPieceType.BUTTERFLY
					&& entry.getValue().getColor() == movingColor) {
				return true;
			}
		}
		return false;
	}

}
