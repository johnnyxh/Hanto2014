/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.epsilon;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.HantoGameFactory;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoBaseTestGame;

/**
 * The specific Epsilon hanto game for testing purposes
 */
public class EpsilonHantoTestGame extends HantoBaseTestGame {
	
	/**
	 * Constructor for GammaHantoTestGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public EpsilonHantoTestGame(HantoPlayerColor movesFirst) {
		testGame = (HantoBaseGame) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.EPSILON_HANTO, movesFirst);
	}

}
