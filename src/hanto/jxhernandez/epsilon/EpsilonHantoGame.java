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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoMove;
import hanto.jxhernandez.common.HantoMoveRule;

/**
 * The specific implementation for an epsilon type hanto game
 */
public class EpsilonHantoGame extends HantoBaseGame {

	/**
	 * Constructor for EpsilonHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public EpsilonHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		// Init pieces
		firstPlayer.addToReserve(HantoPieceType.BUTTERFLY, 1);
		firstPlayer.addToReserve(HantoPieceType.SPARROW, 2);
		firstPlayer.addToReserve(HantoPieceType.CRAB, 6);
		firstPlayer.addToReserve(HantoPieceType.HORSE, 4);
		secondPlayer.addToReserve(HantoPieceType.BUTTERFLY, 1);
		secondPlayer.addToReserve(HantoPieceType.SPARROW, 2);
		secondPlayer.addToReserve(HantoPieceType.CRAB, 6);
		secondPlayer.addToReserve(HantoPieceType.HORSE, 4);
		// Init rules for pieces
		pieceMoves.add(new HantoMoveRule(HantoPieceType.BUTTERFLY, HantoMove.WALK, 1));
		pieceMoves.add(new HantoMoveRule(HantoPieceType.CRAB, HantoMove.WALK, 1));
		pieceMoves.add(new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.FLY, 4));
		pieceMoves.add(new HantoMoveRule(HantoPieceType.HORSE, HantoMove.JUMP));
	}

}
