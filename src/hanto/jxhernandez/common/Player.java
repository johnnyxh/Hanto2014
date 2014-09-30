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

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * The player contains the inventory of pieces out of the board and the cooresponding color of the player
 */
public class Player {
	private HantoPlayerColor color;
	private List<HantoPieceType> reserves;
	
	/**
	 * Constructor for Player.
	 * @param color HantoPlayerColor
	 */
	public Player(HantoPlayerColor color) {
		this.color = color;
		reserves = new ArrayList<HantoPieceType>();
	}
	
	/**
	 * Adds the piece to this player's reserves off the board
	 * @param piece The piece type that belongs to this player
	 */
	public void addToReserve(HantoPieceType piece) {
		reserves.add(piece);
	}
	
	/**
	 * Adds multiple pieces of the same type to this player's reserve
	 * @param piece The piece type being added to this player
	 * @param num The number of pieces being added
	 */
	public void addToReserve(HantoPieceType piece, int num) {
		for(int i=0; i < num; i++) {
			reserves.add(piece);
		}
	}
	
	/**
	 * Removes the piece from this player's reserve
	 * @param piece The piece that is being used from the player's inventory
	
	 * @throws HantoException If the piece is not in the player's reserve */
	public void removeFromReserve(HantoPieceType piece) throws HantoException {
		if (reserves.contains(piece)) {
			reserves.remove(piece);
		} else {
			throw new HantoException("Player does not have this piece in reserve");
		}
	}
	
	/**
	 * Get the number of remaining pieces
	 * @return int of remaining pieces
	 */
	public int getPieceCount() {
		return reserves.size();
	}
	
	/**
	 * Get this player's color
	 * @return The HantoPlayerColor of this player
	 */
	public HantoPlayerColor getPlayerColor() {
		return color;
	}

}
