package hanto.jxhernandez.gamma;

import hanto.common.HantoPlayerColor;

import hanto.jxhernandez.common.HantoBaseTestGame;


public class GammaHantoTestGame extends HantoBaseTestGame {

	public GammaHantoTestGame(HantoPlayerColor movesFirst) {
		testGame = new GammaHantoGame(movesFirst);
	}

}
