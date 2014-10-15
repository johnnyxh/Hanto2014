/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentjxhernandez.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.HantoMove;
import hanto.jxhernandez.common.HantoMoveRule;
import hanto.jxhernandez.common.HantoPosition;
import hanto.jxhernandez.common.Piece;
import hanto.jxhernandez.common.Player;
import hanto.jxhernandez.common.PossibleMoves;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * Simple AI that plays an Epsilon Hanto Game
 */
public class HantoPlayer implements HantoGamePlayer {
	
	private Map<HantoPosition, Piece> board;
	private Player myPlayer;
	private HantoPlayerColor opponentColor;
	private int numTurns;
	private List<HantoMoveRule> gameRules;

	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		board = new HashMap<HantoPosition, Piece>();
		gameRules = new ArrayList<HantoMoveRule>();
		myPlayer = new Player(myColor);
		if (myColor == HantoPlayerColor.BLUE) {
			opponentColor = HantoPlayerColor.RED;
		} else {
			opponentColor = HantoPlayerColor.BLUE;
		}
		// Pieces specific to Epsilon Hanto
		myPlayer.addToReserve(HantoPieceType.BUTTERFLY, 1);
		myPlayer.addToReserve(HantoPieceType.SPARROW, 2);
		myPlayer.addToReserve(HantoPieceType.CRAB, 6);
		myPlayer.addToReserve(HantoPieceType.HORSE, 4);
		// Move rules specific to Epsilon Hanto
		gameRules.add(new HantoMoveRule(HantoPieceType.BUTTERFLY, HantoMove.WALK, 1));
		gameRules.add(new HantoMoveRule(HantoPieceType.CRAB, HantoMove.WALK, 1));
		gameRules.add(new HantoMoveRule(HantoPieceType.SPARROW, HantoMove.FLY, 4));
		gameRules.add(new HantoMoveRule(HantoPieceType.HORSE, HantoMove.JUMP));
		numTurns = 0;
	}

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		// Add opponents move to our board
		if (opponentsMove != null) {
			HantoPosition orig = HantoPosition.coordinateToPosition(opponentsMove.getFrom());
			HantoPosition dest = HantoPosition.coordinateToPosition(opponentsMove.getTo());
			if (orig == null) {
				board.put(dest, new Piece(opponentsMove.getPiece(), opponentColor));
			} else {
				board.remove(orig);
				board.put(dest, new Piece(opponentsMove.getPiece(), opponentColor));
			}
			numTurns++;
		}
		// Perform our move
		if (numTurns == 0) {
			try {
				myPlayer.removeFromReserve(HantoPieceType.BUTTERFLY);
			} catch (HantoException e) {
				e.printStackTrace();
			}
			board.put(new HantoPosition(0, 0), new Piece(HantoPieceType.BUTTERFLY, myPlayer.getPlayerColor()));
			numTurns++;
			return new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoPosition(0, 0));
		} else if (numTurns == 1) {
			try {
				myPlayer.removeFromReserve(HantoPieceType.BUTTERFLY);
			} catch (HantoException e) {
				e.printStackTrace();
			}
			board.put(new HantoPosition(1, 0), new Piece(HantoPieceType.BUTTERFLY, myPlayer.getPlayerColor()));
			numTurns++;
			return new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoPosition(1, 0));
		} else {
			// Evaluate possible options
			List<HantoMoveRecord> possibleMoves = PossibleMoves.listPossibleMoves(board, myPlayer, numTurns, gameRules);
			if (possibleMoves.size() == 0) {
				return new HantoMoveRecord(null, null, null);
			}
			int index = new Random().nextInt(possibleMoves.size());
			HantoPosition orig = HantoPosition.coordinateToPosition(possibleMoves.get(index).getFrom());
			HantoPosition dest = HantoPosition.coordinateToPosition(possibleMoves.get(index).getTo());
			if (orig == null) {
				try {
					myPlayer.removeFromReserve(possibleMoves.get(index).getPiece());
				} catch (HantoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				board.put(dest, new Piece(possibleMoves.get(index).getPiece(), myPlayer.getPlayerColor()));
			} else {
				board.remove(orig);
				board.put(dest, new Piece(possibleMoves.get(index).getPiece(), myPlayer.getPlayerColor()));
			}
			numTurns++;
			return possibleMoves.get(index);
		}
	}

}
