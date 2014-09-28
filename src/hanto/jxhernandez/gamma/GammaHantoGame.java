package hanto.jxhernandez.gamma;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
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

}
