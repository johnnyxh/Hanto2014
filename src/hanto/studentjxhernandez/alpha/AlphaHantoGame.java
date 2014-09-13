/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentjxhernandez.alpha;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import hanto.common.*;
import hanto.studentjxhernandez.common.HantoPosition;
import hanto.studentjxhernandez.common.Piece;

/**
 * Hanto game where each side only gets one butterfly and blue always goes
 * first. Blue must place their butterfly in position (0,0) on the grid. Red
 * then plays their butterfly on a spot adjacent to Blue. The end result for
 * every game of this type is draw.
 */
public class AlphaHantoGame implements HantoGame {

	// I think I moved too fast here
	private Map<HantoCoordinate, HantoPiece> board = new HashMap<HantoCoordinate, HantoPiece>();
	private boolean blueTurn = true;

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		// Butterfly only and there is no chance to move an existing piece
		if (pieceType != HantoPieceType.BUTTERFLY || from != null) {
			throw new HantoException("Illegal Move");
		}
		// Blue goes first and places in 0,0 always
		if (blueTurn && (to.getX() != 0 || to.getY() != 0)) {
			throw new HantoException("Illegal Move");
		}
		// Red's turn needs to place around 0,0
		if (!blueTurn) {
			int distX = Math.abs(to.getX());
			int distY = Math.abs(to.getY());
			if ((distX == 0 && distY == 0) || distX > 1 || distY > 1) {
				throw new HantoException("Illegal Move");
			}
		}
		// Fix for pollice tests
		HantoCoordinate dest = newCords(to.getX(), to.getY());
		// Blue only gets one turn make it false
		if (blueTurn) {
			board.put(dest, new Piece(pieceType, HantoPlayerColor.BLUE));
			blueTurn = false;
			return MoveResult.OK;
		} else {
			board.put(dest, new Piece(pieceType, HantoPlayerColor.RED));
			return MoveResult.DRAW;
		}
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		HantoCoordinate dest = newCords(where.getX(), where.getY());
		return board.get(dest);
	}

	@Override
	public String getPrintableBoard() {
		StringBuilder printBoard = new StringBuilder();
		for (Map.Entry<HantoCoordinate, HantoPiece> entry : board.entrySet()) {
			printBoard
					.append(entry.getValue() + " AT " + entry.getKey() + "\n");
		}
		return printBoard.toString();
	}
	
	private HantoPosition newCords(int x, int y) {
		return new HantoPosition(x, y);
	}

}
