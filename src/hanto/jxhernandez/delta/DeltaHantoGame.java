/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.delta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoMove;
import hanto.jxhernandez.common.HantoMoveRule;

/**
 * Specific implementation for the delta type hanto game
 */
public class DeltaHantoGame extends HantoBaseGame {

	/**
	 * Constructor for DeltaHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
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
