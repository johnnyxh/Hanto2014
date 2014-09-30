/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoBaseGame;
import hanto.jxhernandez.common.HantoMove;
import hanto.jxhernandez.common.HantoMoveRule;

/**
 * The specific implementation for a gamma type hanto game
 */
public class GammaHantoGame extends HantoBaseGame {
	
	private static final int MAX_TURN_NUM = 40;
	
	/**
	 * Constructor for GammaHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		// Initialize inventories
		firstPlayer.addToReserve(HantoPieceType.SPARROW, 5);
		firstPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		secondPlayer.addToReserve(HantoPieceType.SPARROW, 5);
		secondPlayer.addToReserve(HantoPieceType.BUTTERFLY);
		// Initialize piece move rules
		HantoMoveRule sparrowRule = new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.WALK, 1);
		HantoMoveRule butterflyRule = new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.WALK, 1);
		pieceMoves.add(sparrowRule);
		pieceMoves.add(butterflyRule);
	}
	
	@Override
	protected MoveResult postRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		// Check if 20 or more turns taken
		if (numTurns >= MAX_TURN_NUM) {
			return MoveResult.DRAW;
		}
		return null;		
	}
	
	@Override
	protected MoveResult preRuleSetCheck(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		return postRuleSetCheck(pieceType, from, to);
	}

}
