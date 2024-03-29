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
import common.HantoTestGameFactory;
import common.HantoTestGame.PieceLocationPair;

public class DeltaHantoGameTests {

	@Test(expected=HantoPrematureResignationException.class)
	public void resignationAcceptRed() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		assertEquals(MoveResult.BLUE_WINS, testGame.makeMove(null, null, null));
	}
	
	@Test(expected=HantoPrematureResignationException.class)
	public void resignationAcceptBlue() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLUE);
		assertEquals(MoveResult.RED_WINS, testGame.makeMove(null, null, null));
	}
	
	@Test
	public void pieceSparrowFlyingGood() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(1,-1)));
	}
	// I had never checked if an opponents piece was being moved before this
	@Test(expected=HantoException.class)
	public void pieceFlyingGoodButBadMovingOpponentPiece() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLUE);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(-2,0), new HantoPosition(-2,2)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceCrabFlying() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.CRAB, new HantoPosition(1,1), new HantoPosition(1,-1)));
	}
	// I also didn't check if the piece given in makeMove was the piece actually on that space
	@Test(expected=HantoException.class)
	public void attemptingToMoveDifferentPiece() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(1,-1)));
	}
	
	@Test(expected=HantoException.class)
	public void movingAfterWinnerDeclared() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(0,1)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(-1,0)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(1,0)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB, new HantoPosition(1,-1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-1,1)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		// Winner declared here
		assertEquals(MoveResult.BLUE_WINS, testGame.makeMove(HantoPieceType.CRAB, null, new HantoPosition(0,-1)));
		testGame.makeMove(HantoPieceType.CRAB, null, new HantoPosition(-2,2));
	}
	
	@Test
	public void pieceSparrowFlyingGoodNextToEnemy() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(1,1), new HantoPosition(-3,0)));
	}
	
	@Test(expected=HantoException.class)
	public void pieceSparrowFlyingBadNotContiguous() throws HantoException, HantoPrematureResignationException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoPosition(0,0)));
		initialPieces[1] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoPosition(-1,0)));
		initialPieces[2] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(0,1)));
		initialPieces[3] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,1)));
		initialPieces[4] = (new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoPosition(1,1)));
		initialPieces[5] = (new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoPosition(-2,0)));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(3);
		assertEquals(MoveResult.OK, testGame.makeMove(HantoPieceType.SPARROW, new HantoPosition(0,1), new HantoPosition(-3,0)));
	}

}
