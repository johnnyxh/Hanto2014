package epsilonTests;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoPosition;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGameFactory;
import common.HantoTestGame.PieceLocationPair;

public class EpsilonHantoTests {

	@Test
	public void pieceSparrowFlyingGoodDistance4() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(-3,1)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceSparrowFlyingBadDistance5() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(-3,0)));
	}

}
