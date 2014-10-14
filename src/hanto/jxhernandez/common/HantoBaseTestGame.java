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

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;

/**
 * Base class for the HantoTestGames, contains methods and neccesary logic
 */
public abstract class HantoBaseTestGame implements HantoTestGame {

	protected int setPlayerMoving = 0;
	protected HantoBaseGame testGame;

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		boolean blueFirst;
		Player bluePlayer;
		Player redPlayer;
		Map<HantoPosition, Piece> board;
		board = testGame.getBoard();
		board.clear();
		if (testGame.getFirstPlayer().getPlayerColor() == HantoPlayerColor.BLUE) {
			bluePlayer = testGame.getFirstPlayer();
			redPlayer = testGame.getSecondPlayer();
			blueFirst = true;
		} else {
			bluePlayer = testGame.getSecondPlayer();
			redPlayer = testGame.getFirstPlayer();
			blueFirst = false;
		}

		for (int i = 0; i < initialPieces.length; i++) {
			Piece newPiece = new Piece(initialPieces[i].pieceType,
					initialPieces[i].player);
			HantoPosition newPosition = new HantoPosition(
					initialPieces[i].location.getX(),
					initialPieces[i].location.getY());
			if (initialPieces[i].player == HantoPlayerColor.BLUE) {
				try {
					bluePlayer.removeFromReserve(initialPieces[i].pieceType);
					board.put(newPosition, newPiece);
				} catch (HantoException e) {
					e.printStackTrace();
				}
			} else {
				try {
					redPlayer.removeFromReserve(initialPieces[i].pieceType);
					board.put(newPosition, newPiece);
				} catch (HantoException e) {
					e.printStackTrace();
				}
			}
		}

		testGame.setBoard(board);
		if (blueFirst) {
			testGame.setFirstPlayer(bluePlayer);
			testGame.setSecondPlayer(redPlayer);
		} else {
			testGame.setFirstPlayer(redPlayer);
			testGame.setSecondPlayer(bluePlayer);
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		// Since I'm zero based and turn numbers are done by each player moving
		// (0 for blue, 1 for red) I will need to multiply by 2 and subtract 2
		// And add one if the set player to move is the second player
		if (turnNumber == 1) {
			testGame.setNumTurns(0 + setPlayerMoving);
		} else {
			testGame.setNumTurns(((turnNumber * 2) - 2) + setPlayerMoving);
		}
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		if (testGame.getFirstPlayer().getPlayerColor() == player) {
			setPlayerMoving = 0;
		} else {
			setPlayerMoving = 1;
		}

	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException, HantoPrematureResignationException {
		return testGame.makeMove(pieceType, from, to);
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return testGame.getPieceAt(where);
	}

	@Override
	public String getPrintableBoard() {
		return testGame.getPrintableBoard();
	}
	
	public HantoBaseGame getGame() {
		return testGame;
	}

}
