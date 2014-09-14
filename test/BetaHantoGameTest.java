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
	
	@Test
	public void betaHantoGameThirdMoveStillGood() throws HantoException {
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
	
	@Test
	public void betaHantoGameBlueWins() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 1);
		HantoCoordinate thirdMovePos = new HantoPosition(-1, 1);
		HantoCoordinate fourthMovePos = new HantoPosition(-1, 2);
		HantoCoordinate fifthMovePos = new HantoPosition(0, 2);
		HantoCoordinate sixthMovePos = new HantoPosition(1, 1);
		HantoCoordinate seventhMovePos = new HantoPosition(1, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(sixthMovePos).getColor());
		assertEquals(MoveResult.BLUE_WINS, betaGame.makeMove(HantoPieceType.SPARROW, null, seventhMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(seventhMovePos).getColor());
	}
	
	@Test
	public void betaHantoGameRedWins() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(-1, 0);
		HantoCoordinate thirdMovePos = new HantoPosition(1, 0);
		HantoCoordinate fourthMovePos = new HantoPosition(-1, 1);
		HantoCoordinate fifthMovePos = new HantoPosition(0, 1);
		HantoCoordinate sixthMovePos = new HantoPosition(0, -1);
		HantoCoordinate seventhMovePos = new HantoPosition(1, -1);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(sixthMovePos).getColor());
		assertEquals(MoveResult.RED_WINS, betaGame.makeMove(HantoPieceType.SPARROW, null, seventhMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(seventhMovePos).getColor());
	}
	
	@Test(expected=HantoException.class)
	public void betaHantoGameQueenMustBePlacedBlue() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(-1, 0);
		HantoCoordinate thirdMovePos = new HantoPosition(-2, 0);
		HantoCoordinate fourthMovePos = new HantoPosition(-3, 0);
		HantoCoordinate fifthMovePos = new HantoPosition(-4, 0);
		HantoCoordinate sixthMovePos = new HantoPosition(-5, -0);
		HantoCoordinate seventhMovePos = new HantoPosition(-6, -0);
		betaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(sixthMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		betaGame.makeMove(HantoPieceType.SPARROW, null, seventhMovePos);
	}
	
	@Test(expected=HantoException.class)
	public void betaHantoGameQueenMustBePlacedRed() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(-1, 0);
		HantoCoordinate thirdMovePos = new HantoPosition(-2, 0);
		HantoCoordinate fourthMovePos = new HantoPosition(-3, 0);
		HantoCoordinate fifthMovePos = new HantoPosition(-4, 0);
		HantoCoordinate sixthMovePos = new HantoPosition(-5, -0);
		HantoCoordinate seventhMovePos = new HantoPosition(-6, -0);
		HantoCoordinate eighthMovePos = new HantoPosition(-7, 0);
		betaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(sixthMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, seventhMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(seventhMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		betaGame.makeMove(HantoPieceType.SPARROW, null, eighthMovePos);
	}
	
	@Test
	public void betaHantoGameButterflyPlacedOn4th() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(-1, 0);
		HantoCoordinate thirdMovePos = new HantoPosition(-2, 0);
		HantoCoordinate fourthMovePos = new HantoPosition(-3, 0);
		HantoCoordinate fifthMovePos = new HantoPosition(-4, 0);
		HantoCoordinate sixthMovePos = new HantoPosition(-5, -0);
		HantoCoordinate seventhMovePos = new HantoPosition(-6, -0);
		HantoCoordinate eighthMovePos = new HantoPosition(-7, 0);
		betaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(sixthMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, seventhMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(seventhMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, eighthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(eighthMovePos).getColor());
	}
	
	@Test
	public void betaHantoGameRunningOutOfSparrowsBLUE() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(-1, 0);
		HantoCoordinate thirdMovePos = new HantoPosition(-2, 0);
		HantoCoordinate fourthMovePos = new HantoPosition(-3, 0);
		HantoCoordinate fifthMovePos = new HantoPosition(-4, 0);
		HantoCoordinate sixthMovePos = new HantoPosition(-5, -0);
		HantoCoordinate seventhMovePos = new HantoPosition(-6, -0);
		HantoCoordinate eighthMovePos = new HantoPosition(-7, 0);
		betaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, secondMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(sixthMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, seventhMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(seventhMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, eighthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(eighthMovePos).getColor());
	}
	
	
}
