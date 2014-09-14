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
			throw new HantoException("Illegal Move");
		}
		// First move of the game, second move of the game, then every other
		if (numTurns == 0 && (to.getX() != 0 || to.getY() != 0)) {
			throw new HantoException("Illegal Move");
		} else if (numTurns == 1) {
			int distX = Math.abs(to.getX());
			int distY = Math.abs(to.getY());
			if ((distX == 0 && distY == 0) || distX > 1 || distY > 1) {
				throw new HantoException("Illegal Move");
			}
		} else if (numTurns >= 2 && !isValidMove(dest, getPlayerTurn())) {
			throw new HantoException("Illegal Move");
		}
		board.put(dest, new Piece(pieceType, getPlayerTurn()));
		numTurns++;
		return MoveResult.OK;
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

	// I somewhat jumped the gun when making this method. It implemented the hanto
	// Placement rules (cant be next to enemy pieces, must be next to one friendly
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
		// Check if the selected movement is next to or ontop of a friendly piece
		// Needs to be next to atleast one (and not on top of another friendly)
		for (int i=0; i < friendlyPieces.size(); i++) {
			if (to.equals(friendlyPieces.get(i))) {
				return false;
			}
			if (to.isNextTo(friendlyPieces.get(i))) {
				isValid = true;
			}
		}
		return isValid;
	}

}
