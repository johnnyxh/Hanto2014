package hanto.jxhernandez.common;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.gamma.GammaHantoGame;
import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;

public abstract class HantoBaseTestGame implements HantoTestGame {

	protected HantoBaseGame testGame;

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		boolean blueFirst;
		Player bluePlayer;
		Player redPlayer;
		Map<HantoPosition, Piece> board;
		board = testGame.getBoard();
		board.clear();
		if (testGame.getFirstPlayer().getPlayerColor() == HantoPlayerColor.BLUE) {
			bluePlayer = testGame.getFirstPlayer();
			redPlayer = testGame.getSecondPlayer();
			blueFirst = true;
		} else {
			bluePlayer = testGame.getSecondPlayer();
			redPlayer = testGame.getFirstPlayer();
			blueFirst = false; 
		}
		
		for (int i=0; i < initialPieces.length; i++) {
			Piece newPiece = new Piece(initialPieces[i].pieceType, initialPieces[i].player);
			HantoPosition newPosition = new HantoPosition(initialPieces[i].location.getX(), initialPieces[i].location.getY());
			if (initialPieces[i].player == HantoPlayerColor.BLUE) {
				try {
					bluePlayer.removeFromReserve(initialPieces[i].pieceType);
					board.put(newPosition, newPiece);
				} catch (HantoException e) {
					e.printStackTrace();
				}
			} else {
				try {
					redPlayer.removeFromReserve(initialPieces[i].pieceType);
					board.put(newPosition, newPiece);
				} catch (HantoException e) {
					e.printStackTrace();
				}
			}
		}
		
		testGame.setBoard(board);
		if (blueFirst) {
			testGame.setFirstPlayer(bluePlayer);
			testGame.setSecondPlayer(redPlayer);
		} else {
			testGame.setFirstPlayer(redPlayer);
			testGame.setSecondPlayer(bluePlayer);
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		testGame.setNumTurns(turnNumber-1);
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		if (!(testGame.getPlayerTurn().getPlayerColor() == player)) {
			Player tempPlayer = testGame.getFirstPlayer();
			testGame.setFirstPlayer(testGame.getSecondPlayer());
			testGame.setSecondPlayer(tempPlayer);
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		return testGame.makeMove(pieceType, from, to);
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return testGame.getPieceAt(where);
	}

	@Override
	public String getPrintableBoard() {
		return testGame.getPrintableBoard();
	}

}
