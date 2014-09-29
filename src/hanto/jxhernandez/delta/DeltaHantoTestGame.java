package hanto.jxhernandez.delta;

import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.HantoBaseTestGame;

public class DeltaHantoTestGame extends HantoBaseTestGame {
	
	public DeltaHantoTestGame(HantoPlayerColor movesFirst) {
		testGame = new DeltaHantoGame(movesFirst);
	}

}
