/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package alphaBetaTests;
import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.jxhernandez.HantoGameFactory;
import hanto.jxhernandez.common.HantoPosition;

import org.junit.Test;

public class BetaHantoGameTest {

	@Test
	public void betaHantoGameFirstMoveGood() throws HantoException, HantoPrematureResignationException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		assertEquals(MoveResult.OK,
				betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	@Test(expected = HantoException.class)
	public void betaHantoGameFirstMoveBad() throws HantoException, HantoPrematureResignationException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 1);
		assertEquals(MoveResult.OK,
				betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	@Test
	public void betaHantoGameRedFirst() throws HantoException, HantoPrematureResignationException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(firstMovePos)
				.getColor());
	}

	@Test
	public void betaHantoGameBlueFirst() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameSecondMoveGood() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameSecondMoveBad() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameThirdMoveGood() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameThirdMoveStillGood() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameBlueWins() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameRedWins() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameQueenMustBePlacedBlue() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameQueenMustBePlacedRed() throws HantoException, HantoPrematureResignationException {
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
	public void betaHantoGameButterflyPlacedOn4thAndPrintBoard() throws HantoException, HantoPrematureResignationException {
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
		System.out.println(betaGame.getPrintableBoard());
	}
	
	@Test
	public void betaHantoGameRunningOutOfSparrowsBLUE() throws HantoException, HantoPrematureResignationException {
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
		HantoCoordinate ninthMovePos = new HantoPosition(-8, 0);
		HantoCoordinate tenthMovePos = new HantoPosition(-9, 0);
		HantoCoordinate eleventhMovePos = new HantoPosition(-10, 0);
		HantoCoordinate twelvthMovePos = new HantoPosition(-11, 0);
		HantoCoordinate thirteenthMovePos = new HantoPosition(-12, 0);
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
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, ninthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(ninthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, tenthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(tenthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, eleventhMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(eleventhMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, twelvthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(twelvthMovePos).getColor());
		// Out of pieces
		assertEquals(MoveResult.DRAW, betaGame.makeMove(HantoPieceType.SPARROW, null, thirteenthMovePos));
	}
	
	@Test
	public void betaHantoGameRunningOutOfSparrowsRED() throws HantoException, HantoPrematureResignationException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(-1, 0);
		HantoCoordinate thirdMovePos = new HantoPosition(-2, 0);
		HantoCoordinate fourthMovePos = new HantoPosition(-3, 0);
		HantoCoordinate fifthMovePos = new HantoPosition(-4, 0);
		HantoCoordinate sixthMovePos = new HantoPosition(-5, -0);
		HantoCoordinate seventhMovePos = new HantoPosition(-6, -0);
		HantoCoordinate eighthMovePos = new HantoPosition(-7, 0);
		HantoCoordinate ninthMovePos = new HantoPosition(-8, 0);
		HantoCoordinate tenthMovePos = new HantoPosition(-9, 0);
		HantoCoordinate eleventhMovePos = new HantoPosition(-10, 0);
		HantoCoordinate twelvthMovePos = new HantoPosition(-11, 0);
		HantoCoordinate thirteenthMovePos = new HantoPosition(-12, 0);
		betaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(firstMovePos)
				.getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, secondMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(secondMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, thirdMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(thirdMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fourthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(fourthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, fifthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(fifthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, sixthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(sixthMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, seventhMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(seventhMovePos).getColor());
		// On this turn the butterfly must be placed else throw exception
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, eighthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(eighthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, ninthMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(ninthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, tenthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(tenthMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, eleventhMovePos));
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(eleventhMovePos).getColor());
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.SPARROW, null, twelvthMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(twelvthMovePos).getColor());
		// Out of pieces
		assertEquals(MoveResult.DRAW, betaGame.makeMove(HantoPieceType.SPARROW, null, thirteenthMovePos));
	}
	
	@Test(expected=HantoException.class)
	public void betaHantoGamePlacingSecondButterfly() throws HantoException, HantoPrematureResignationException {
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
		assertEquals(MoveResult.OK, betaGame.makeMove(HantoPieceType.BUTTERFLY, null, thirdMovePos));
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(thirdMovePos).getColor());
	}
	
}
