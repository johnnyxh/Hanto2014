package hanto.jxhernandez.delta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.HantoBaseGame;

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
	}

}
