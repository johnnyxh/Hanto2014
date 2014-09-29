package hanto.jxhernandez.gamma;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.HantoGameFactory;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoBaseTestGame;


public class GammaHantoTestGame extends HantoBaseTestGame {

	public GammaHantoTestGame(HantoPlayerColor movesFirst) {
		testGame = (HantoBaseGame) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.GAMMA_HANTO, movesFirst);
	}

}
