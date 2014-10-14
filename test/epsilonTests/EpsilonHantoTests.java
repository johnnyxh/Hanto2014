package epsilonTests;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.jxhernandez.common.HantoBaseTestGame;
import hanto.jxhernandez.common.HantoPosition;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGameFactory;
import common.HantoTestGame.PieceLocationPair;

public class EpsilonHantoTests {

	@Test
	public void pieceSparrowFlyingGoodDistance4() throws HantoException, HantoPrematureResignationException {
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
	public void pieceSparrowFlyingBadDistance5() throws HantoException, HantoPrematureResignationException {
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
	
	@Test
	public void pieceHorseJumpGoodPositiveVertical() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,-2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,3)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(0,-2), new HantoPosition(0,4)));
	}
	
	@Test
	public void pieceHorseJumpGoodNegativeVertical() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,-1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,-2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,-3)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(0,2), new HantoPosition(0,-4)));
	}
	
	@Test
	public void pieceHorseJumpGoodPositiveRisingDiagonal() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(-1,0)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(2,0)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(-2,0)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(3,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(-2,0), new HantoPosition(4,0)));
	}
	
	@Test
	public void pieceHorseJumpGoodNegativeRisingDiagonal() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,0)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(2,0)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(-3,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(2,0), new HantoPosition(-4,0)));
	}
	
	@Test
	public void pieceHorseJumpGoodPositiveDescendingDiagonal() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(1,-1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(-1,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(2,-2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(-2,2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(3,-3)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(-2,2), new HantoPosition(4,-4)));
	}
	
	@Test
	public void pieceHorseJumpGoodNegativeDescendingDiagonal() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,-1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(2,-2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(-3,3)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(2,-2), new HantoPosition(-4,4)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceHorseJumpBadNotStraightLine() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(1,-1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(-1,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(2,-2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(-2,2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(3,-3)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(-2,2), new HantoPosition(3,-4)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceHorseJumpBadEmptyHexes() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(-1,2)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,-1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(1,1)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.HORSE, new HantoPosition(0,-1), new HantoPosition(0,3)));
	}
	
	@Test(expected=HantoPrematureResignationException.class)
	public void earlyResignationPiecesLeftToPlace() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,-1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(2,-2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(-3,3)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(null, null, null));
	}
	
	@Test(expected=HantoPrematureResignationException.class)
	public void earlyResignationAllPiecesPlacedMovementsLeft() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[26];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,-1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,2)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,-2)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,3)));
		initialPieces[6] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-3)));
		initialPieces[7] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,4)));
		initialPieces[8] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-4)));
		initialPieces[9] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,5)));
		initialPieces[10] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-5)));
		initialPieces[11] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,6)));
		initialPieces[12] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-6)));
		initialPieces[13] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,7)));
		initialPieces[14] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-7)));
		initialPieces[15] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,8)));
		initialPieces[16] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(0,-8)));
		initialPieces[17] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(0,9)));
		initialPieces[18] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,-8)));
		initialPieces[19] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE, new HantoPosition(0,9)));
		initialPieces[20] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,-8)));
		initialPieces[21] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE, new HantoPosition(0,9)));
		initialPieces[22] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,-8)));
		initialPieces[23] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE, new HantoPosition(0,9)));
		initialPieces[24] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.HORSE, new HantoPosition(0,-8)));
		initialPieces[25] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE, new HantoPosition(0,9)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(13);
		assertEquals(MoveResult.OK, testGame.makeMove(null, null, null));
	}
	
	@Test
	public void properResignation() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[4];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB, new HantoPosition(-1,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,-1)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(2);
		((HantoBaseTestGame) testGame).getGame().getPlayerTurn().removeAllReserve();
		assertEquals(MoveResult.BLUE_WINS, testGame.makeMove(null, null, null));
	}

}
