package hanto.studentjxhernandez.beta;

import java.util.HashMap;
import java.util.Map;

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
	
	private Map<HantoCoordinate, HantoPiece> board = new HashMap<HantoCoordinate, HantoPiece>();
	private int numTurns;
	private HantoPlayerColor movesFirst;
	private HantoPlayerColor movesSecond;
	
	public BetaHantoGame (HantoPlayerColor movesFirst) {
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
		for (Map.Entry<HantoCoordinate, HantoPiece> entry : board.entrySet()) {
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

}
