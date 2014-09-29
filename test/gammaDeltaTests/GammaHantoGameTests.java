package gammaDeltaTests;

import static org.junit.Assert.*;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoPosition;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;
import common.HantoTestGameFactory;

public class GammaHantoGameTests {

	@Test
	public void placingPieceNextToFriendly() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(1,0)));
	}
	
	@Test(expected=HantoException.class)
	public void placingPieceNextToFriendlyAndEnemy() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(-1,1)));
	}
	
	@Test(expected=HantoException.class)
	public void placingPieceNextToEnemy() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(-2,1)));
	}

}
