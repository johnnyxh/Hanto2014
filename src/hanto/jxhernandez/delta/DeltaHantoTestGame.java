package hanto.jxhernandez.delta;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.HantoGameFactory;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoBaseTestGame;

public class DeltaHantoTestGame extends HantoBaseTestGame {
	
	public DeltaHantoTestGame(HantoPlayerColor movesFirst) {
		testGame = (HantoBaseGame) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.DELTA_HANTO, movesFirst);
	}

}
