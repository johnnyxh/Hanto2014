package hanto.jxhernandez.delta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoMove;
import hanto.jxhernandez.common.HantoMoveRule;

public class DeltaHantoGame extends HantoBaseGame {

	public DeltaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		//Initializing pieces
		firstPlayer.addToReserve(HantoPieceType.CRAB, 4);
		firstPlayer.addToReserve(HantoPieceType.SPARROW, 4);
		firstPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		secondPlayer.addToReserve(HantoPieceType.CRAB, 4);
		secondPlayer.addToReserve(HantoPieceType.SPARROW, 4);
		secondPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		//Initializing piece moves
		pieceMoves.add(new HantoMoveRule(HantoPieceType.BUTTERFLY, HantoMove.WALK, 1));
		pieceMoves.add(new HantoMoveRule(HantoPieceType.CRAB, HantoMove.WALK, 1));
		pieceMoves.add(new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.FLY));
	}

}
