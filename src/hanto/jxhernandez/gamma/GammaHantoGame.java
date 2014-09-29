package hanto.jxhernandez.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoMove;
import hanto.jxhernandez.common.HantoMoveRule;

public class GammaHantoGame extends HantoBaseGame {
	
	private static final int MAX_TURN_NUM = 20;
	
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		// Initialize inventories
		firstPlayer.addToReserve(HantoPieceType.SPARROW, 5);
		firstPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		secondPlayer.addToReserve(HantoPieceType.SPARROW, 5);
		secondPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		// Initialize piece move rules
		HantoMoveRule sparrowRule = new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.WALK, 1);
		HantoMoveRule butterflyRule = new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.WALK, 1);
		pieceMoves.add(sparrowRule);
		pieceMoves.add(butterflyRule);
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
