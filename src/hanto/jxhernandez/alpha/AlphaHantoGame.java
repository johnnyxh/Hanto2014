/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.alpha;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoPosition;
import hanto.jxhernandez.common.Piece;

/**
 * Hanto game where each side only gets one butterfly and blue always goes
 * first. Blue must place their butterfly in position (0,0) on the grid. Red
 * then plays their butterfly on a spot adjacent to Blue. The end result for
 * every game of this type is draw.
 */
public class AlphaHantoGame implements HantoGame {

	// I think I moved too fast here
	private Map<HantoPosition, HantoPiece> board = new HashMap<HantoPosition, HantoPiece>();
	private boolean blueTurn = true;

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		HantoPosition dest = newCords(to.getX(), to.getY());
		if (from != null) {
			throw new HantoException("Illegal Move");
		}
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
			HantoPosition edgePosition1 = new HantoPosition(1, 1);
			HantoPosition edgePosition2 = new HantoPosition(-1, -1);
			boolean edgeCase = dest.equals(edgePosition1) || dest.equals(edgePosition2);
			if ((distX == 0 && distY == 0) || distX > 1 || distY > 1 || edgeCase) {
				throw new HantoException("Illegal Move");
			}
		}
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
		for (Entry<HantoPosition, HantoPiece> entry : board.entrySet()) {
			printBoard
					.append(entry.getValue() + " AT " + entry.getKey() + "\n");
		}
		return printBoard.toString();
	}
	
	private HantoPosition newCords(int x, int y) {
		return new HantoPosition(x, y);
	}

}
