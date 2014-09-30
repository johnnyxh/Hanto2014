/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.common;

import hanto.common.HantoPieceType;

/**
 * Object that is used to attatch a move validator to pieces in specific Hanto Games
 */
public class HantoMoveRule {

	private HantoPieceType piece;
	private HantoMoveValidator moveValidator;
	
	/**
	 * Constructor for HantoMoveRule.
	 * @param piece HantoPieceType
	 * @param move HantoMove
	 * @param numSpaces int
	 */
	public HantoMoveRule(HantoPieceType piece, HantoMove move, int numSpaces) {
		this.piece = piece;
		moveValidator = HantoMoveFactory.getInstance().makeMoveValidator(move, numSpaces);
	}
	
	/**
	 * Constructor for HantoMoveRule.
	 * @param piece HantoPieceType
	 * @param move HantoMove
	 */
	public HantoMoveRule(HantoPieceType piece, HantoMove move) {
		this.piece = piece;
		moveValidator = HantoMoveFactory.getInstance().makeMoveValidator(move);
	}

	public HantoPieceType getPiece() {
		return piece;
	}
	
	public HantoMoveValidator getMoveValidator() {
		return moveValidator;
	}
} 
