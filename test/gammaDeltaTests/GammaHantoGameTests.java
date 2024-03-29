package gammaDeltaTests;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoPosition;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;
import common.HantoTestGameFactory;

public class GammaHantoGameTests {
	
	
	@Test
	public void firstTurnPlacement() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(0,0)));
	}
	
	@Test(expected=HantoException.class)
	public void firstTurnPlacementBad() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(0,1)));
	}
	
	@Test
	public void secondTurnPlacement() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(0,0));
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(1,0)));
	}
	
	@Test(expected=HantoException.class)
	public void secondTurnPlacementBad() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(0,0));
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(2,0)));
	}

	@Test
	public void placingPieceNextToFriendly() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(1);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(1,0)));
	}
	
	@Test(expected=HantoException.class)
	public void placingPieceNextToFriendlyAndEnemy() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(1);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(-1,1)));
	}
	
	@Test(expected=HantoException.class)
	public void placingPieceNextToEnemy() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(1);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(-2,1)));
	}
	
	@Test
	public void drawAtTwentyTurns() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[2];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		testGame.initializeBoard(initialPieces);
		// Skipping turns, as long as nobody won
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(20);
		assertEquals(MoveResult.DRAW, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(-2,0)));
	}
	
	@Test
	public void pieceWalkingGood() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(1,0)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceWalkingBadMoreThanOneHexNoAdjacents() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(2,0)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceWalkingBadMoreThanOneHexFriendlyAdjacent() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(1,-1)));
	}
	
	@Test
	public void pieceWalkingGoodMoveOneAdjacentEnemy() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-1,2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(-1,2), new HantoPosition(-2,2)));
	}
	
	@Test
	public void pieceWalkingGoodMoveOneAdjacentEnemyAndFriendly() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-1,2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(-1,2), new HantoPosition(-1,1)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceWalkingNonContiguousResult() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(0,1), new HantoPosition(0,2)));
		System.out.println("Non-Contiguous Test: Move should be undone");
		System.out.println(testGame.getPrintableBoard());
	}
	
	@Test
	public void pieceWalkingNonContiguousResultPrintOut() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(3);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		try {
			assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(0,1), new HantoPosition(0,2)));
		} catch (HantoException e) {}
		System.out.println("Non-Contiguous Test: Move should be undone");
		System.out.println(testGame.getPrintableBoard());
	}
	/*
	@Test
	public void winnerOnFinalTurn() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-1,0)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,0)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,-1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-1,1)));
		testGame.initializeBoard(initialPieces);
		// Skipping turns, as long as nobody won
		testGame.setTurnNumber(20);
		assertEquals(MoveResult.BLUE_WINS, testGame.makeMove(HantoPieceType.SPARROW, null, new HantoPosition(0,-1)));
	}
	*/

}
