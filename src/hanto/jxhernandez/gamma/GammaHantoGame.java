package hanto.jxhernandez.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoBaseGame;

public class GammaHantoGame extends HantoBaseGame {
	
	private static final int MAX_TURN_NUM = 20;
	
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		// Initialize inventories
		firstPlayer.addToReseve(HantoPieceType.SPARROW, 5);
		firstPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		secondPlayer.addToReseve(HantoPieceType.SPARROW, 5);
		secondPlayer.addToReserve(HantoPieceType.BUTTERFLY);
	}
	
	@Override
	protected MoveResult postRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		// Check if 20 or more turns taken
		if (numTurns >= MAX_TURN_NUM) {
			return MoveResult.DRAW;
		}
		return null;		
	}
	
	@Override
	protected MoveResult preRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		return postRuleSetCheck(pieceType, from, to);
	}

}
