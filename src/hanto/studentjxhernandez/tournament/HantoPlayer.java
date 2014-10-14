package hanto.studentjxhernandez.tournament;

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
			numTurns++;
			return new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoPosition(0,0));
		} else if (numTurns == 1) {
			try {
				myPlayer.removeFromReserve(HantoPieceType.BUTTERFLY);
			} catch (HantoException e) {
				e.printStackTrace();
			}
			numTurns++;
			return new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoPosition(1,0));
		} else {
			// Evaluate possible options
			List<HantoMoveRecord> possibleMoves = PossibleMoves.listPossibleMoves(board, myPlayer, numTurns, gameRules);
			int index = new Random().nextInt(possibleMoves.size());
			numTurns++;
			return possibleMoves.get(index);
		}
	}

}
