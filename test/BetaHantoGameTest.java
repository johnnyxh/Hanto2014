import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentjxhernandez.common.HantoPosition;

import org.junit.Test;

public class BetaHantoGameTest {

	@Test
	public void betaHantoGameFirstMoveGood() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		assertEquals(MoveResult.OK,
				betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	@Test(expected = HantoException.class)
	public void betaHantoGameFirstMoveBad() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 1);
		assertEquals(MoveResult.OK,
				betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	@Test
	public void betaHantoGameRedFirst() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(firstMovePos)
				.getColor());
	}

	@Test
	public void betaHantoGameBlueFirst() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
	}

	// Checking to see that Blue and Red successfully alternate and that second
	// player's move is valid
	@Test
	public void betaHantoGameSecondMoveGood() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 1);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
	}
	
	@Test(expected=HantoException.class)
	public void betaHantoGameSecondMoveBad() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 2);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos));
	}
	
	@Test
	public void betaHantoGameThirdMoveGood() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 1);
		HantoCoordinate thirdMovePos = new HantoPosition(-1, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
	}
	
	@Test(expected=HantoException.class)
	public void betaHantoGameThirdMoveBad() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 1);
		HantoCoordinate thirdMovePos = new HantoPosition(-1, 1);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
	}
}
